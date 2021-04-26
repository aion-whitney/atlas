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

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Whitney Hunter
 * @since 1.8.0
 */
public class ReflectionBasedUpdateMapperTest {

    @Test
    void testMap() {
        // set up the fixture
        String value1 = "test";
        int value2 = 1;

        ReflectionBasedUpdateMapper<FromClass, ToClass> mapper = new ReflectionBasedUpdateMapper<>();
        FromClass from = new FromClass(value1, value2);
        ToClass to = new ToClass();

        // execute the SUT
        Optional<ToClass> result = mapper.map(from, to);

        // validation
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getValue1()).isEqualTo(value1);
        assertThat(result.get().getValue2()).isEqualTo(value2);
    }

    @Data
    @AllArgsConstructor
    private static class FromClass {
        private String value1;
        private int value2;
    }

    @Data
    private static class ToClass {
        private String value1;
        private int value2;
    }
}
