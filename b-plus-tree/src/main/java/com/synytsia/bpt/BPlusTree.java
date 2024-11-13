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
        // TODO
        //  - Find correct leaf
        //  - Iterate leaf keys to find needed key
        throw new ExerciseNotCompletedException();
    }

    /**
     * Inserts key into tree. Duplicates are not supported
     *
     * @param key new key to insert
     * @return {@literal true} if key is inserted, {@literal false} if it's already in the tree
     */
    public boolean insert(Integer key) {
        // TODO
        //  - Find correct leaf
        // - Insert key into correct position, return false if key alredy exists
        // - Handle leaf split
        throw new ExerciseNotCompletedException();
    }

    Node getRoot() {
        throw new ExerciseNotCompletedException();
    }

/*
    ┌────────────────────────────────────────────────────────────────────────────────────────┐
    │  HINTS: If you are stuck, use methods below. Each method is a piece of the algorithm.  │
    └────────────────────────────────────────────────────────────────────────────────────────┘
 */
/*
    private LeafNode findLeaf(Integer key, Node node) {
        // TODO:
        //     - Use recursive/iterative approach:
        //     - if node is leaf, it's found, return it
        //     - otherwise go to child i, so that node.keys[i-1] <= key < node.keys[i]. In details:
        //          - iterate through node.keys
        //          - if key < node.keys[i] -> go to node.children[i]
        //     - don't forget that keys.size + 1 == children.size. So if key >= node.keys.last -> go to node.children.last
        throw new ExerciseNotCompletedException();
    }

    private boolean insertIntoLeaf(final LeafNode leaf, final Integer key) {
        // TODO:
        //      - Iterate through keys
        //      - if key == leaf.keys[i] -> don't insert, return false
        //      - if key < leaf.keys[i] -> insert key at position i, return true
        //      - if key > leaf.keys.last -> insert key at the end, return true
        throw new ExerciseNotCompletedException();
    }

    private void splitLeaf(final LeafNode leaf) {
        // TODO:
        //      - Create new leaf
        //      - MOVE keys [m,n) to new leaf
        //      - Remember that leafs form linked list, so use sibling field to insert new leaf right after current leaf. Just as you do in linked list
        //      - Don't forget to handle case when leaf is root (create new root, establish relationship parent/child between leaf and new root)
        //      - Promote middle key (newLeaf.keys.first) to parent
        //
        throw new ExerciseNotCompletedException();
    }

    private void promoteToParent(final InnerNode parent, final Integer key, final Node newChild) {
        // TODO:
        //      - Establish relationship parent/child with newChild (assign newChild parent and insert key and newChild at correct position into the parent)
                        Keep in mind that key >= any new child key, so if you are inserting key at position i, insert child at i+1 to follow B+ tree rules
        //      - Handle case if parent oveflows (keys == order) -> split inner node
        throw new ExerciseNotCompletedException();
    }

    private void splitInner(final InnerNode innerNode) {
        // TODO:
        //      - Create new inner node
        //      - MOVE keys [m,n) to new node
        //      - MOVE children [m+1,n) to new node, remeber to reasign theirs parent
        //      - Don't forget to handle case when node is root (create new root, establish relationship parent/child between node and new root)
        //      - Promote middle key (newInner.keys.first) to parent. REMOVE promoted key - "when promoting key in inner node - it's kicked out"
        throw new ExerciseNotCompletedException();
    }

*/
}
