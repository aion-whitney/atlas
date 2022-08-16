/*
 * Copyright 2022 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.atlas.mapping.impl;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringAfter;

/**
 * A {@link OneWayUpdateMapper} that maps fields with matching names using getters/setters and reflection.
 *
 * @author Whitney Hunter
 * @since 0.1.6
 */
@Slf4j
public class ReflectionBasedUpdateMapper<FROM, TO> implements OneWayUpdateMapper<FROM, TO> {

    private Map<String, OneWayMapper<Object, Object>> submappers = new HashMap();

    private Predicate<Method> isSetMethod = method -> startsWith(method.getName(), "set");

    private Function<Method, String> propertyFromSetMethod = method -> substringAfter(method.getName(), "set");

    /**
     * Map the properties from the `from` object to correspondingly named properties in the `to` object. Use
     * getter/setter methods.
     *
     * @param from The object to map from.
     * @param to The object to map to.
     * @return The mapped to object.
     */
    @Override
    public Optional<TO> map(FROM from, TO to) {
        Arrays.stream(to.getClass().getDeclaredMethods())
                .filter(isSetMethod)
                .forEach(setMethod -> {
                    getPropertyValue(from, propertyFromSetMethod.apply(setMethod))
                            .ifPresent(value -> setValue(to, setMethod, value));
                });

        return Optional.of(to);
    }

    public void addsubmapper(String propertyName, OneWayMapper<Object, Object> mapper) {
        submappers.put(propertyName, mapper);
    }

    /**
     * Get the value of a property from the given object with the given property name.
     *
     * @param from The object from which the property is desired.
     * @param propertyName The name of the desired property.
     * @return The property value if it can be determined.
     */
    private Optional<Object> getPropertyValue(FROM from, String propertyName) {
        return findGetMethodForProperty(from.getClass(), propertyName)
                .map(m -> {
                    try {
                        return m.invoke(from);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        log.error("Unable to get value for property: {}", propertyName);
                        return null;
                    }
                });
    }

    /**
     * Call a given set method with the given value on the to object.
     *
     * @param to The object on which to call the set method.
     * @param setMethod The set method to call.
     * @param value The value to set.
     */
    private void setValue(TO to, Method setMethod, Object value) {
        try {
            String propertyName = propertyFromSetMethod.apply(setMethod);
            OneWayMapper<Object, Object> submapper = submappers.get(propertyName);
            Object val = submapper != null ? submapper.map(value) : value;
            setMethod.invoke(to, val);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Unable to set property value", e);
        }
    }

    /**
     * Look for a get method on the given class for the given property name.
     *
     * @param clazz The class on which to look
     * @param propertyName The to find a setter for.
     * @return
     */
    private Optional<Method> findGetMethodForProperty(Class<?> clazz, String propertyName) {
        try {
            String getMethodName = "get" + propertyName;
            return Optional.of(clazz.getMethod(getMethodName));
        } catch (NoSuchMethodException e) {
            log.info("Unable to find getter method for property: {}", propertyName);
            return Optional.empty();
        }
    }

}
