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
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Whitney Hunter
 * @since 0.1.6
 */
public class ReflectionCompoundOneWayMapperTest {

    @Test
    void testSimpleMapping() {
        // set up the fixture
        OneWayMapper<TestEntity1, TestModel1> assembler = new ReflectionCompoundOneWayMapper(TestModel1.class) {
        };

        TestEntity1 entity = new TestEntity1();
        entity.setId(UUID.randomUUID());
        entity.setValue("VALUE");

        // execute the SUT
        Optional<TestModel1> result = assembler.map(entity);

        // validation
        assertThat(result).isPresent();
        assertThat(result.get().getValue()).isEqualTo("VALUE");
    }

    @Test
    void testCollectionMapping() {
        // set up the fixture
        OneWayMapper<TestEntity2, TestModel2> assembler = new ReflectionCompoundOneWayMapper(TestModel2.class) {
        };

        TestEntity2 entity = new TestEntity2();
        entity.setId(UUID.randomUUID());
        entity.setValue(Arrays.asList("VALUE1", "VALUE2"));

        // execute the SUT
        Optional<TestModel2> result = assembler.map(entity);

        // validation
        assertThat(result).isPresent();
        assertThat(result.get().getValue()).hasSize(2);
        assertThat(result.get().getValue()).contains("VALUE1", "VALUE2");
    }


    @Test
    void testWithSubMapper() {
        // set up the fixture
        CompoundOneWayMapper<TestEntity1, TestModel1> assembler = new ReflectionCompoundOneWayMapper(TestModel1.class) {
        };
        assembler.addSubMapper("value", new OneWayMapper<String, String>() {
            @Override
            public Optional<String> map(String s) {
                return Optional.of(s.toUpperCase());
            }
        });

        TestEntity1 entity = new TestEntity1();
        entity.setId(UUID.randomUUID());
        entity.setValue("value");

        // execute the SUT
        Optional<TestModel1> result = assembler.map(entity);

        // validation
        assertThat(result).isPresent();
        assertThat(result.get().getValue()).isEqualTo("VALUE");
    }


    @Data
    private static class TestEntity1 {
        private UUID id;
        private String value;
    }

    @Data
    private static class TestModel1 {
        private String value;

        public TestModel1(TestEntity1 testEntity1) {
        }
    }

    @Data
    private static class TestEntity2 {
        private UUID id;
        private List<String> value;
    }

    @Data
    private static class TestModel2 {
        private List<String> value;

        public TestModel2(TestEntity2 testEntity2) {
        }
    }

}
