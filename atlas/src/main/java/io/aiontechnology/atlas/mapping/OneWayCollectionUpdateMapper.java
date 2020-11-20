package io.aiontechnology.atlas.mapping;

import java.util.Collection;

/**
 * This is a functional interface that represents a mapping from a collection of one type to another by updating the
 * given destination collection.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface OneWayCollectionUpdateMapper<FROM, TO> {

    /**
     * Map the 'from' object to the given collection of 'TO' objects.
     * <p>
     * This type of mapping will have side effects. The given 'to' collection will be changed in the process.
     *
     * @param from The collection to map from.
     * @param to The collection to map to.
     * @return The resulting collection of mapped object.
     */
    Collection<TO> map(Collection<FROM> from, Collection<TO> to);

}
