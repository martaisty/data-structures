package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

import java.util.ArrayList;
import java.util.List;

abstract sealed class Node permits InnerNode, LeafNode {


    abstract boolean isLeaf();

    List<Integer> getKeys() {
        throw new ExerciseNotCompletedException();
    }

    InnerNode getParent() {
        throw new ExerciseNotCompletedException();
    }
}
