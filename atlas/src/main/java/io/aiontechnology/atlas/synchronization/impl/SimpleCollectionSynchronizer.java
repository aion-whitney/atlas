package io.aiontechnology.atlas.synchronization.impl;

import io.aiontechnology.atlas.synchronization.CollectionSynchronizer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Sync the collection passed in as toSync to match the collection passed in as syncFrom
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class SimpleCollectionSynchronizer<T> implements CollectionSynchronizer<T> {

    /**
     * Synchronize the given toSync collection with the syncFrom collection.
     *
     * @param toSync The collection to sync to.
     * @param syncFrom The collection to sync from.
     * @return The resulting synced collection.
     */
    public Collection<T> sync(Collection<T> toSync, Collection<T> syncFrom) {
        Collection<T> toRemove = new ArrayList<>(toSync);
        toRemove.removeAll(syncFrom);
        Collection<T> toAdd = new ArrayList<>(syncFrom);
        toAdd.removeAll(toSync);

        toSync.removeAll(toRemove);
        toSync.addAll(toAdd);
        return toSync;
    }

}
