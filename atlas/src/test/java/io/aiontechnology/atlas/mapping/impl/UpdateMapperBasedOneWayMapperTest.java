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

import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for {@link UpdateMapperBasedOneWayMapper}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class UpdateMapperBasedOneWayMapperTest {

    @Test
    void testHappyPath() throws Exception {
        // setup the fixture
        OneWayUpdateMapper<String, Destination> mapper = (string, destination) -> {
            destination.setValue(string);
            return Optional.of(destination);
        };

        UpdateMapperBasedOneWayMapper updateMapperBasedOneWayMapper =
                new UpdateMapperBasedOneWayMapper(mapper, Destination.class);

        // execute the SUT
        Optional<Destination> result = updateMapperBasedOneWayMapper.map("hello");

        // validation
        assertThat(result.isPresent(), is(true));
        assertThat(result.get().getValue(), is("hello"));
    }

    @Test
    void testBadClass() throws Exception {
        // setup the fixture
        OneWayUpdateMapper<String, Destination> mapper = (string, destination) -> {
            destination.setValue(string);
            return Optional.of(destination);
        };

        UpdateMapperBasedOneWayMapper updateMapperBasedOneWayMapper =
                new UpdateMapperBasedOneWayMapper(mapper, String.class);

        // execute the SUT and validate
        assertThrows(ClassCastException.class, () -> updateMapperBasedOneWayMapper.map("hello"));
    }

    @Data
    private static final class Destination {
        private String value;
    }

}
