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
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Whitney Hunter
 * @since 0.1.6
 */
@Slf4j
public abstract class CompoundOneWayMapper<FROM, TO> implements OneWayMapper<FROM, TO> {

    // Mappers
    private Map<String, OneWayMapper> subMappers = new HashMap<>();

    protected OneWayMapper getSubMapper(String key) {
        OneWayMapper subMapper = subMappers.get(key);
        if (subMapper == null) {
            log.debug("Unable to find mapper with key: {}", key);
        }
        return subMapper;
    }

    protected void addSubMapper(String key, OneWayMapper<?, ?> subMapper) {
        subMappers.put(key, subMapper);
    }

}
