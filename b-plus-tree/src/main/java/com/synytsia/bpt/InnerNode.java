package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

import java.util.ArrayList;

/**
 * In addition to parent class has the following fields:
 * <ol>
 *     <li>Children - uses for navigation to find correct leaf</li>
 * </ol>
 */
final class InnerNode extends Node {

    /**
     * @return {@literal false}
     */
    @Override
    boolean isLeaf() {
        throw new ExerciseNotCompletedException();
    }

    ArrayList<Node> getChildren() {
        throw new ExerciseNotCompletedException();
    }
}
