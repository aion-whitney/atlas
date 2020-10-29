package io.aiontechnology.atlas.mapping.impl;

import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
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
