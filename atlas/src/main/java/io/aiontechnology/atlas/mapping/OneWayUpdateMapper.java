package io.aiontechnology.atlas.mapping;

import java.util.Optional;

/**
 * This is a functional interface that represents a mapping from one type to another by updating the given destination
 * object.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface OneWayUpdateMapper<FROM, TO> {

    /**
     * Map the given value to an instance of the TO type. It is valid for the mapping to return {@link Optional#empty()}
     * for any reason that makes sense to the mapping.
     * <p>
     * This type of mapping will have side effects. The given 'to' object will be changed in the process.
     *
     * @param from The object to map from.
     * @return The mapped object or {@link Optional#empty()}.
     */
    Optional<TO> map(FROM from, TO to);

}
