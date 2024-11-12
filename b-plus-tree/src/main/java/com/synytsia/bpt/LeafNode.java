package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

final class LeafNode extends Node {

    @Override
    boolean isLeaf() {
        throw new ExerciseNotCompletedException();
    }

    LeafNode getNextSibling() {
        throw new ExerciseNotCompletedException();
    }
}
