package io.aiontechnology.atlas.mapping;

import java.util.Collection;

/**
 * This is a functional interface that represents a mapping from a collection of one type to a collection of another.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface OneWayCollectionMapper<FROM, TO> {

    /**
     * Map the collection of 'from' objects to a collection of 'TO' objects.
     * <p>
     * This type of mapping should not have side effects. The from collection (or objects in it) should not be changed
     * in the process.
     *
     * @param from The object to map from.
     * @return The resulting collection of mapped object.
     */
    Collection<TO> map(Collection<FROM> from);

}
