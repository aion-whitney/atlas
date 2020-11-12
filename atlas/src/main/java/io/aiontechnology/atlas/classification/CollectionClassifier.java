package io.aiontechnology.atlas.classification;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A functional interface for objects that separate a collection of elements into a map of lists.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface CollectionClassifier<KEY, VALUE> {

    /**
     * Split the given collection into a map of lists.
     *
     * @param values The collection to split
     * @return The separated map.
     */
    Map<KEY, List<VALUE>> classify(Collection<VALUE> values);

}
