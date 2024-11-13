package com.synytsia.bpt;

import com.synytsia.exceptions.ExerciseNotCompletedException;

/**
 * In addition to parent class has the following fields:
 * <ol>
 *     <li>Next sibling - used for sequential access of data</li>
 *     <li>Values - actual data. <b>NOT used</b> in the task <b>don't</b> add it</li>
 * </ol>
 */
final class LeafNode extends Node {

    /**
     * @return {@literal true}
     */
    @Override
    boolean isLeaf() {
        throw new ExerciseNotCompletedException();
    }

    LeafNode getNextSibling() {
        throw new ExerciseNotCompletedException();
    }
}
