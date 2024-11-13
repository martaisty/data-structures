package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

/**
 * Define the following fields:
 * <ol>
 *     <li>Order - maximum number of children node can have</li>
 *     <li>Root - root of the tree. Can be either leaf (at the beginning) or inner (when tree growths) node</li>
 * </ol>
 */
public class BPlusTree {

    /**
     * @param order order of tree
     * @throws IllegalArgumentException when order is less than 3
     */
    public BPlusTree(final int order) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Searches key in tree, returns it if found or null otherwise
     *
     * @param key key to find
     * @return key if found in tree, {@literal null} otherwise
     */
    public Integer get(Integer key) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Inserts key into tree. Duplicates are not supported
     *
     * @param key new key to insert
     * @return {@literal true} if key is inserted, {@literal false} if it's already in the tree
     */
    public boolean insert(Integer key) {
        throw new ExerciseNotCompletedException();
    }

    Node getRoot() {
        throw new ExerciseNotCompletedException();
    }

}
