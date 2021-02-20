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

import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An implementation of {@link OneWayCollectionMapper} that uses a corresponding {@link OneWayMapper}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class SimpleOneWayCollectionMapper<FROM, TO> implements OneWayCollectionMapper<FROM, TO> {

    /** The mapper that is used to map each item in the 'from' collection */
    private final OneWayMapper<FROM, TO> mapper;

    /**
     * Map the objects in the 'from' collection to a collection of objects of TO type.
     *
     * @param from The collection to map from.
     * @return The resulting collection of objects of type TO.
     */
    @Override
    public Collection<TO> map(Collection<FROM> from) {
        if (from == null) {
            return Collections.EMPTY_LIST;
        }
        return from.stream()
                .map(mapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
