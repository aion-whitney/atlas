package io.aiontechnology.atlas.synchronization.impl;

import io.aiontechnology.atlas.synchronization.impl.SimpleCollectionSynchronizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link SimpleCollectionSynchronizer}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class SimpleCollectionSynchronizerTest {

    @Test
    void testDisjoint() throws Exception {
        // setup the fixture
        Collection<String> toSync = new ArrayList<>();
        toSync.add("one");
        toSync.add("two");
        Collection<String> syncFrom = List.of("three", "four");

        SimpleCollectionSynchronizer<String> syncHelper = new SimpleCollectionSynchronizer<>();

        // execute the SUT
        Collection<String> result = syncHelper.sync(toSync, syncFrom);

        // validation
        assertThat(result.size(), is(2));
        assertThat(result, hasItems("three", "four"));
    }

    @Test
    void testOverlap() throws Exception {
        // setup the fixture
        Collection<String> toSync = new ArrayList<>();
        toSync.add("one");
        toSync.add("two");
        Collection<String> syncFrom = List.of("two", "three");

        SimpleCollectionSynchronizer<String> syncHelper = new SimpleCollectionSynchronizer<>();

        // execute the SUT
        Collection<String> result = syncHelper.sync(toSync, syncFrom);

        // validation
        assertThat(result.size(), is(2));
        assertThat(result, hasItems("two", "three"));
    }

    @Test
    void testEmptyToSync() throws Exception {
        // setup the fixture
        Collection<String> toSync = new ArrayList<>();
        Collection<String> syncFrom = List.of("two", "three");

        SimpleCollectionSynchronizer<String> syncHelper = new SimpleCollectionSynchronizer<>();

        // execute the SUT
        Collection<String> result = syncHelper.sync(toSync, syncFrom);

        // validation
        assertThat(result.size(), is(2));
        assertThat(result, hasItems("two", "three"));
    }

    @Test
    void testEmptySyncFrom() throws Exception {
        // setup the fixture
        Collection<String> toSync = new ArrayList<>();
        toSync.add("one");
        toSync.add("two");
        Collection<String> syncFrom = List.of();

        SimpleCollectionSynchronizer<String> syncHelper = new SimpleCollectionSynchronizer<>();

        // execute the SUT
        Collection<String> result = syncHelper.sync(toSync, syncFrom);

        // validation
        assertThat(result.size(), is(0));
    }

    @Test
    void testBothEmpty() throws Exception {
        // setup the fixture
        Collection<String> toSync = new ArrayList<>();
        Collection<String> syncFrom = List.of();

        SimpleCollectionSynchronizer<String> syncHelper = new SimpleCollectionSynchronizer<>();

        // execute the SUT
        Collection<String> result = syncHelper.sync(toSync, syncFrom);

        // validation
        assertThat(result.size(), is(0));
    }

}
