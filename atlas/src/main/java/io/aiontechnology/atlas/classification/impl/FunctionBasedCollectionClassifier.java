package io.aiontechnology.atlas.classification.impl;

import io.aiontechnology.atlas.classification.CollectionClassifier;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Separate a collection of elements into a map of lists using the given classification function.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class FunctionBasedCollectionClassifier<KEY, VALUE> implements CollectionClassifier<KEY, VALUE> {

    /** A function that is used to obtain a KEY_TYPE from the given VALUE_TYPE */
    private final Function<VALUE, KEY> classificationFunction;

    /**
     * Split the given collection into a map of lists using the {@link #classificationFunction}.
     *
     * @param values The collection to split
     * @return The separated map.
     */
    public Map<KEY, List<VALUE>> classify(Collection<VALUE> values) {
        return values.stream()
                .collect(Collectors.groupingBy(classificationFunction::apply));
    }

}
