package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

final class LeafNode extends Node {

    /*
     * TODO:
     *  Declare sibling element( hint: it's always leaf node)
     *
     */

    @Override
    boolean isLeaf() {
        throw new ExerciseNotCompletedException();
    }

    LeafNode getNextSibling() {
        throw new ExerciseNotCompletedException();
    }
}
