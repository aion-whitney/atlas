package io.aiontechnology.atlas.mapping;

import java.util.Optional;

/**
 * This is a functional interface that represents a mapping from two objects to another.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface OneWayBiMapper<FROM1, FROM2, TO> {

    /**
     * Map the 'from' object to the 'TO' type.
     *
     * @param from1 The first object to map from.
     * @param from2 The second object to map from.
     * @return The resulting mapped object.
     */
    Optional<TO> map(FROM1 from1, FROM2 from2);

}
