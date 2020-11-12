package io.aiontechnology.atlas.mapping.impl;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Collection<Integer> result = stringIntegerSimpleOneWayCollectionMapper.map(from);

        // validation
        assertThat(result.size(), is(0));
    }

    @Test
    void testNonEmptyFrom() throws Exception {
        // setup the fixture
        OneWayMapper<String, Integer> oneWayMapper = string -> Optional.of(string.length());
        SimpleOneWayCollectionMapper<String, Integer> stringIntegerSimpleOneWayCollectionMapper =
                new SimpleOneWayCollectionMapper<>(oneWayMapper);

        Collection<String> from = List.of("One", "Two", "Three", "Four");

        // execute the SUT
        Collection<Integer> result = stringIntegerSimpleOneWayCollectionMapper.map(from);

        // validation
        assertThat(result.size(), is(4));
        assertThat(result, hasItems(3, 3, 5, 4));
    }

}
