package io.aiontechnology.atlas.synchronization;

import java.util.Collection;

/**
 * A functional interface for objects that synchronize a collection from onother collection.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@FunctionalInterface
public interface CollectionSynchronizer<T> {

    /**
     * Synchronize the given toSync collection with the syncFrom collection.
     *
     * @param toSync The collection to sync to.
     * @param syncFrom The collection to sync from.
     * @return The resulting synced collection.
     */
    Collection<T> sync(Collection<T> toSync, Collection<T> syncFrom);

}
