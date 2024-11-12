package com.synytsia.bpt;

import java.util.ArrayList;
import java.util.List;

abstract sealed class Node permits InnerNode, LeafNode {

    final ArrayList<Integer> keys;
    InnerNode parent;

    public Node() {
        this.keys = new ArrayList<>();
    }

    abstract boolean isLeaf();

    List<Integer> getKeys() {
        return keys;
    }

    InnerNode getParent() {
        return parent;
    }
}
