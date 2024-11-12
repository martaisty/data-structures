package com.synytsia.bpt;

public class BPlusTree {
    private final int order;
    private Node root;

    /**
     * @param order order of tree
     * @throws IllegalArgumentException when order is less than 3
     */
    public BPlusTree(final int order) {
        if (order < 3) {
            throw new IllegalArgumentException();
        }

        this.order = order;
        this.root = new LeafNode();
    }

    /**
     * Searches key in tree, returns it if found or null otherwise
     *
     * @param key key to find
     * @return key if found in tree, {@literal null} otherwise
     */
    public Integer get(Integer key) {
        final var leaf = findLeaf(key, root);

        int i = leaf.findIndex(key);
        return i == -1
                ? null
                : leaf.keys.get(i);
    }

    /**
     * Inserts key into tree. Duplicates are not supported
     *
     * @param key new key to insert
     * @return {@literal true} if key is inserted, {@literal false} if it's already in the tree
     */
    public boolean insert(Integer key) {
        final var leaf = findLeaf(key, root);

        final var inserted = leaf.insert(key);
        if (leaf.keys.size() == order) {
            splitLeaf(leaf);
        }

        return inserted;
    }

    Node getRoot() {
        return this.root;
    }

    private LeafNode findLeaf(final Integer key, final Node node) {
        if (node.isLeaf()) {
            return (LeafNode) node;
        }

        final var innerNode = (InnerNode) node;
        for (int i = 0; i < innerNode.keys.size(); i++) {
            if (key < innerNode.keys.get(i)) {
                return findLeaf(key, innerNode.children.get(i));
            }
        }

        return findLeaf(key, innerNode.children.getLast());
    }

    private void splitLeaf(final LeafNode leaf) {
        int mid = leaf.keys.size() / 2;
        final var rightLeaf = new LeafNode();

        final var newKeys = leaf.keys.subList(mid, leaf.keys.size());
        rightLeaf.keys.addAll(newKeys);
        // Deleting the old one
        newKeys.clear();

        rightLeaf.nextSibling = leaf.nextSibling;
        leaf.nextSibling = rightLeaf;

        if (leaf == root) {
            leaf.parent = new InnerNode();
            leaf.parent.children.add(leaf);
            root = leaf.parent;
        }

        rightLeaf.parent = leaf.parent;
        // Promote to parent
        promoteToParent(rightLeaf, rightLeaf.keys.getFirst());
    }

    private void promoteToParent(final Node newNode, final Integer key) {
        final var parent = newNode.parent;
        parent.insert(key, newNode);

        if (parent.keys.size() == order) {
            splitInner(parent);
        }
    }


    private void splitInner(final InnerNode innerNode) {
        final int mid = innerNode.keys.size() / 2;

        final var rightInner = new InnerNode();

        final var newKeys = innerNode.keys.subList(mid, innerNode.keys.size());
        rightInner.keys.addAll(newKeys);
        newKeys.clear();

        final var newChildren = innerNode.children.subList(mid + 1, innerNode.children.size());
        rightInner.children.addAll(newChildren);
        newChildren.forEach(child -> child.parent = rightInner);
        newChildren.clear();

        if (innerNode == root) {
            innerNode.parent = new InnerNode();
            innerNode.parent.children.add(innerNode);
            root = innerNode.parent;
        }

        rightInner.parent = innerNode.parent;

        promoteToParent(rightInner, rightInner.keys.removeFirst());
    }


}
