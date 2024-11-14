package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for nodes that contains common fields:
 * <ol>
 *     <li>Parent - used for navigation to promote key</li>
 *     <li>Keys - navigation in inner nodes, key for actual data in leafs</li>
 * </ol>
 */
abstract sealed class Node permits InnerNode, LeafNode {


    abstract boolean isLeaf();

    List<Integer> getKeys() {
        throw new ExerciseNotCompletedException();
    }

    InnerNode getParent() {
        throw new ExerciseNotCompletedException();
    }
}
