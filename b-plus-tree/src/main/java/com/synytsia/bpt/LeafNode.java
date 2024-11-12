package com.synytsia.bpt;

import java.util.Objects;

final class LeafNode extends Node {

    LeafNode nextSibling;

    public LeafNode() {
    }

    @Override
    boolean isLeaf() {
        return true;
    }

    LeafNode getNextSibling() {
        return nextSibling;
    }

    public int findIndex(final Integer key) {
        for (int i = 0; i < keys.size(); i++) {
            if (Objects.equals(key, keys.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public boolean insert(final Integer key) {
        for (int i = 0; i < keys.size(); i++) {
            if(Objects.equals(key, keys.get(i))) {
                return false;
            } else if (key < keys.get(i)) {
                keys.add(i, key);
                return true;
            }
        }
        keys.addLast(key);
        return true;
    }
}
