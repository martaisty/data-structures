package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

import java.util.ArrayList;

final class InnerNode extends Node {

    /*
    TODO:
     Declare: children
     */

    @Override
    boolean isLeaf() {
        throw new ExerciseNotCompletedException();
    }

    ArrayList<Node> getChildren() {
        throw new ExerciseNotCompletedException();
    }
}
