package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

public class BPlusTree {
    /*
    TODO
    - Declare order field (will be useful during nodes overflow)
    - Declare root
     */

    /**
     * @param order order of tree
     * @throws IllegalArgumentException when order is less than 3
     */
    public BPlusTree(final int order) {
        /*
        TODO init fields (root initially is a leaf)
         */
        throw new ExerciseNotCompletedException();
    }

    /**
     * Searches key in tree, returns it if found or null otherwise
     *
     * @param key key to find
     * @return key if found in tree, {@literal null} otherwise
     */
    public Integer get(Integer key) {
        /*
        TODO:
          - locate potential leaf (use keys array to navigate to correct child, start from root)
          - search in leaf for the key
         */
        throw new ExerciseNotCompletedException();
    }

    /**
     * Inserts key into tree. Duplicates are not supported
     *
     * @param key new key to insert
     * @return {@literal true} if key is inserted, {@literal false} if it's already in the tree
     */
    public boolean insert(Integer key) {
        /*
        TODO
            - Navigate correct leaf (the same approach as for search)
            - Insert into leaf
            - Deal with overflow (if occurs leaf keys.size == order)
         */
        throw new ExerciseNotCompletedException();
    }

    Node getRoot() {
        throw new ExerciseNotCompletedException();
    }

    private LeafNode findLeaf(final Integer key, final Node node) {
        /*
        TODO
            - If it's leaf - found
            - else use keys to navigate to next child
            - repeat recursively
         */
        throw new ExerciseNotCompletedException();
    }

    private boolean insertIntoLeaf(final Integer key, final LeafNode leaf) {
        /*
        TODO
            - if key already exist in leaf - return false
            - otherwise insert into correct position ( keys[i] < keys[i+1])
            - return true
         */
    }

    private void splitLeaf(final LeafNode leaf) {

    }

    private void promoteToParent(final Node newNode, final Integer key) {

    }

    private void splitInner(final InnerNode innerNode) {

    }

}
