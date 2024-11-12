package com.synytsia.bpt;

import java.util.*;

public class TreeUtils {

    static Map<Integer, List<Node>> collect(final Node root) {
        final var levels = new TreeMap<Integer, List<Node>>();
        final var queue = new LinkedList<LevelNode>();

        queue.push(new LevelNode(0, root));

        while (!queue.isEmpty()) {
            final var current = queue.removeFirst();
            final var nodes = levels.computeIfAbsent(current.level(), k -> new LinkedList<>());

            nodes.addLast(current.node());
            if (!current.node().isLeaf()) {
                final var innerNode = (InnerNode) current.node();
                innerNode.getChildren()
                        .forEach(node -> queue.addLast(new LevelNode(current.level() + 1, node)));
            }
        }

        return levels;
    }

    private record LevelNode(int level, Node node) {
    }
}
