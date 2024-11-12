package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

import java.util.ArrayList;
import java.util.List;

abstract sealed class Node permits InnerNode, LeafNode {

    /**
     * TODO:
     * 1. Declare keys
     * 2. Declare parent node (hint: it's always inner node type)
     */


    abstract boolean isLeaf();

    List<Integer> getKeys() {
        throw new ExerciseNotCompletedException();
    }

    InnerNode getParent() {
        throw new ExerciseNotCompletedException();
    }
}
