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
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SimpleOneWayCollectionMapper}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class SimpleOneWayCollectionMapperTest {

    @Test
    void testEmptyFrom() throws Exception {
        // setup the fixture
        OneWayMapper<String, Integer> oneWayMapper = string -> Optional.of(string.length());
        SimpleOneWayCollectionMapper<String, Integer> stringIntegerSimpleOneWayCollectionMapper =
                new SimpleOneWayCollectionMapper<>(oneWayMapper);

        Collection<String> from = Collections.emptyList();

        // execute the SUT
        Optional<Collection<Integer>> result = stringIntegerSimpleOneWayCollectionMapper.map(from);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNonEmptyFrom() throws Exception {
        // setup the fixture
        OneWayMapper<String, Integer> oneWayMapper = string -> Optional.of(string.length());
        SimpleOneWayCollectionMapper<String, Integer> stringIntegerSimpleOneWayCollectionMapper =
                new SimpleOneWayCollectionMapper<>(oneWayMapper);

        Collection<String> from = List.of("One", "Two", "Three", "Four");

        // execute the SUT
        Optional<Collection<Integer>> result = stringIntegerSimpleOneWayCollectionMapper.map(from);

        // validation
        assertThat(result).isPresent();
        assertThat(result.get().size()).isEqualTo(4);
        assertThat(result.get()).contains(3, 3, 5, 4);
    }

}
