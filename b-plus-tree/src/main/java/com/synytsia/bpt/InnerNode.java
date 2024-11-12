package com.synytsia.bpt;

import java.util.ArrayList;

final class InnerNode extends Node {

    final ArrayList<Node> children;

    public InnerNode() {
        this.children = new ArrayList<>();
    }

    @Override
    boolean isLeaf() {
        return false;
    }

    ArrayList<Node> getChildren() {
        return children;
    }

    public void insert(final Integer key, final Node newChild) {
        for (int i = 0; i < keys.size(); i++) {
            if (key < keys.get(i)) {
                keys.add(i, key);
                children.add(i + 1, newChild);
                return;
            }
        }
        keys.addLast(key);
        children.addLast(newChild);
    }
}
