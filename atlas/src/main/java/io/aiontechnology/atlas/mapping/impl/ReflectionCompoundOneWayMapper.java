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

import io.aiontechnology.atlas.exception.ModelObjectConstructionError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.1.6
 */
@Slf4j
public abstract class ReflectionCompoundOneWayMapper<FROM, TO> extends CompoundOneWayMapper<FROM, TO> {

    private final Class<TO> modelClass;

    public ReflectionCompoundOneWayMapper(Class<TO> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public Optional<TO> map(FROM from) {
        TO to = createModel(from);
        Arrays.stream(from.getClass().getDeclaredFields())
                .map(Field::getName)
                .forEach(fieldName -> {
                    fieldGetMethod(from.getClass(), fieldName).ifPresent(method -> {
                        var entityValue = invokeMethod(method, from);
                        var mappedEntityValue = Optional.ofNullable(getSubMapper(fieldName))
                                .flatMap(mapper -> mapper.map(entityValue))
                                .orElse(entityValue);
                        fieldSetMethod(modelClass, fieldName, method.getReturnType())
                                .ifPresent(setMethod -> invokeMethod(setMethod, to, mappedEntityValue));
                    });
                });
        return Optional.of(to);
    }

    private Optional<Method> fieldGetMethod(Class<?> clazz, String fieldName) {
        try {
            return Optional.ofNullable(clazz.getMethod("get" + StringUtils.capitalize(fieldName)));
        } catch (NoSuchMethodException e) {
            log.debug("Unable to find get method for field {}", fieldName);
            return Optional.empty();
        }
    }

    private Optional<Method> fieldSetMethod(Class<?> clazz, String fieldName, Class<?> fieldType) {
        try {
            return Optional.ofNullable(clazz.getMethod("set" + StringUtils.capitalize(fieldName), fieldType));
        } catch (NoSuchMethodException e) {
            log.debug("Unable to find set method for field {}", fieldName);
            return Optional.empty();
        }
    }

    private Object invokeMethod(Method method, Object object, Object... parameters) {
        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to invoke method", e);
        }
    }

    private TO createModel(FROM from) {
        try {
            Constructor<TO> modelCtor = modelClass.getConstructor(from.getClass());
            TO to = modelCtor.newInstance(from);
            return to;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            String message = "Unable to create instance for class: " + from.getClass().getName();
            throw new ModelObjectConstructionError(message, e);
        }
    }

}
