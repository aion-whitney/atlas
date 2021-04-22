/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

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
