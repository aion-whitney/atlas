/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.aiontechnology.atlas.mapping.impl;

import io.aiontechnology.atlas.mapping.OneWayMapper;

import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.2.0
 */
public class ReflexiveOneWayMapper<FROM, TO> implements OneWayMapper<FROM, TO> {

    @Override
    public Optional<TO> map(FROM from) {
        return Optional.empty();
    }

}
