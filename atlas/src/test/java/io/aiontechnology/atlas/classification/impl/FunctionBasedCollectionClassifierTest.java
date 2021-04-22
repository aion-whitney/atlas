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

package io.aiontechnology.atlas.classification.impl;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


/**
 * Tests for {@link FunctionBasedCollectionClassifier}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class FunctionBasedCollectionClassifierTest {

    @Test
    void testClassify() throws Exception {
        // setup the fixture
        Function<String, Character> classificationFunction = input -> input.charAt(0);
        FunctionBasedCollectionClassifier<Character, String> classifier = new FunctionBasedCollectionClassifier<>(classificationFunction);

        Collection<String> collection = List.of("apple", "acorn", "bottle");

        // execute the SUT
        Map<Character, List<String>> result = classifier.classify(collection);

        // validation
        assertThat(result, notNullValue());
        assertThat(result.size(), is(2));
        assertThat(result.get('a').size(), is(2));
        assertThat(result.get('a'), hasItems("apple", "acorn"));
        assertThat(result.get('b').size(), is(1));
        assertThat(result.get('b'), hasItems("bottle"));
    }

}
