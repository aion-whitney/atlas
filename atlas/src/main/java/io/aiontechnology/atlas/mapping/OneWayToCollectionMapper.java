package io.aiontechnology.atlas.mapping;

import java.util.Collection;

/**
 * This is a functional interface that represents a mapping from one type to a collection of another.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface OneWayToCollectionMapper<FROM, TO> {

    /**
     * Map the 'from' object to a collection of 'to' objects.
     * <p>
     * This type of mapping should not have side effects. The from object should not be changed in the process.
     *
     * @param from The object to map from.
     * @return The resulting collection of mapped object.
     */
    Collection<TO> map(FROM from);

}
