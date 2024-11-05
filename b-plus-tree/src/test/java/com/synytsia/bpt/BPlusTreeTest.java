package com.synytsia.bpt;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class BPlusTreeTest {

    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class EmptyTree {

        @Order(1)
        @ParameterizedTest(name = "Min order is 3")
        @ValueSource(ints = {-1, 0, 1, 2})
        void throwsWhenOrderLessThanThree(final int order) {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new BPlusTree(order),
                    "Doesn't throw when order of tree is below minimum");
        }

        @Order(2)
        @Test
        void rootIsLeaf() {
            final var emptyTree = new BPlusTree(3);

            assertTrue(emptyTree.getRoot().isLeaf(), "Root is not leaf in empty tree");
        }

        @Order(3)
        @Test
        void nextSiblingIsNull() {
            final var emptyTree = new BPlusTree(3);

            final var leaf = (LeafNode) emptyTree.getRoot();

            assertNull(leaf.getNextSibling(), "Sibling of the single leaf in tree is not null");
        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InsertionWithoutSplit {

        @Order(1)
        @Test
        void insertSingleValue() {
            final var tree = new BPlusTree(3);
            assertTrue(tree.insert(2), "Didn't return true when inserted key");
        }

        @Order(2)
        @Test
        void insertDuplicateValue() {
            final var tree = new BPlusTree(3);
            tree.insert(2);
            assertFalse(tree.insert(2), "Didn't return false when inserted duplicate key");
        }

        public static Stream<Arguments> keysAreOrdered() {
            return Stream.of(
                    arguments(3, List.of(10, 5)),
                    arguments(4, List.of(10, 5)),
                    arguments(4, List.of(10, 1, 5))
            );
        }

        @Order(3)
        @DisplayName("Keys are sorted in ascending order")
        @ParameterizedTest(name = "Tree order {0}, inserted keys {1}")
        @MethodSource
        void keysAreOrdered(final int order, final List<Integer> keysToInsert) {
            final var tree = new BPlusTree(order);
            keysToInsert.forEach(tree::insert);
            final var root = tree.getRoot();

            final var sortedKeys = keysToInsert.stream()
                    .sorted()
                    .toList();

            assertTrue(root.isLeaf(), "Root is not leaf");
            assertEquals(sortedKeys, root.getKeys(), "Keys are not ordered");
        }

        @Order(4)
        @DisplayName("Leaf doesn't split when full")
        @ParameterizedTest(name = "Tree order {0}")
        @ValueSource(ints = {3, 4, 8})
        void fullLeafDoesntSplit(final int order) {
            final int keysSize = order - 1;
            final var tree = initTreeWithOrderAndKeysInserted(order, keysSize);
            final var root = tree.getRoot();

            assertTrue(root.isLeaf(), "Root is not leaf");
            assertEquals(keysSize, root.getKeys().size(), "Root doesn't have all keys");
        }

        @Order(5)
        @Test
        void nextSiblingIsNull() {
            final var emptyTree = new BPlusTree(3);

            final var leaf = (LeafNode) emptyTree.getRoot();

            assertNull(leaf.getNextSibling(), "Sibling of the single leaf in tree is not null");
        }
    }

    @Order(3)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class LeafSplit {

        @Order(1)
        @Test
        @DisplayName("Initial leaf splits (it's root), new root created")
        void leafRootSplit() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 3);
            final var root = tree.getRoot();


            final var expectedLeafKeys = List.of(
                    List.of(9),
                    List.of(12, 23)
            );
            final var expectedRootKey = 12;

            assertFalse(root.isLeaf(), "Root is leaf");
            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var actualLeafKeys = treeNodes.get(1).stream()
                    .map(Node::getKeys)
                    .toList();

            assertEquals(expectedLeafKeys, actualLeafKeys, "Leafs have invalid keys: inserted into invalid leaf or split incorrectly");
            assertEquals(1, root.getKeys().size(), "Root has to have 1 key after initial split");
            assertEquals(expectedRootKey, root.getKeys().getFirst(), "Invalid key promoted to parent");
        }

        @Order(2)
        @Test
        @DisplayName("Right leaf splits")
        void rightLeafSplit() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 3);
            tree.insert(15);

            final var root = tree.getRoot();


            final var expectedLeafKeys = List.of(
                    List.of(9),
                    List.of(12),
                    List.of(15, 23)
            );
            final var expectedRootKeys = List.of(12, 15);

            assertFalse(root.isLeaf(), "Root is leaf");
            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var actualLeafKeys = treeNodes.get(1).stream()
                    .map(Node::getKeys)
                    .toList();

            assertEquals(expectedLeafKeys, actualLeafKeys, "Leafs have invalid keys: inserted into invalid leaf or split incorrectly");
            assertEquals(expectedRootKeys, root.getKeys(), "Root has invalid keys: invalid key promoted to parent or order is not maintained");
        }

        @Order(3)
        @Test
        @DisplayName("Left leaf splits")
        void leftLeafSplit() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 5);

            final var root = tree.getRoot();


            final var expectedLeafKeys = List.of(
                    List.of(2),
                    List.of(5, 9),
                    List.of(12, 23)
            );
            final var expectedRootKeys = List.of(5, 12);

            assertFalse(root.isLeaf(), "Root is leaf");
            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var actualLeafKeys = treeNodes.get(1).stream()
                    .map(Node::getKeys)
                    .toList();

            assertEquals(expectedLeafKeys, actualLeafKeys, "Leafs have invalid keys: inserted into invalid leaf or split incorrectly");
            assertEquals(expectedRootKeys, root.getKeys(), "Root has invalid keys: invalid key promoted to parent or order is not maintained");
        }

        @Order(4)
        @Test
        @DisplayName("Leafs form linked list")
        void leafsAreLinked() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 3);

            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var leafs = treeNodes.get(1);

            for (int i = 0; i < leafs.size() - 1; i++) {
                assertSame(leafs.get(i + 1), ((LeafNode) leafs.get(i)).getNextSibling(), "Leaf %d has incorrect next sibling".formatted(i));
            }
            assertNull(((LeafNode) leafs.getLast()).getNextSibling(), "Sibling of last node is not null");
        }
    }

    @Order(4)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InnerNodeSplit {

        @Order(1)
        @Test
        @DisplayName("Inner node split (it's root), new root created")
        void innerRootSplit() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 6);
            final var root = tree.getRoot();


            final var expectedLeafKeys = List.of(
                    List.of(2),
                    List.of(5),
                    List.of(7, 9),
                    List.of(12, 23)
            );
            final var expectedInnerKeys = List.of(
                    List.of(5),
                    List.of(12)
            );
            final var expectedRootKey = 7;

            assertFalse(root.isLeaf(), "Root is leaf");
            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var actualLeafKeys = treeNodes.get(2).stream()
                    .map(Node::getKeys)
                    .toList();
            final var actualInnerKeys = treeNodes.get(1).stream()
                    .map(Node::getKeys)
                    .toList();

            assertEquals(expectedLeafKeys, actualLeafKeys, "Leafs have invalid keys: inserted into invalid leaf or split incorrectly");
            assertEquals(expectedInnerKeys, actualInnerKeys, "Inner nodes have invalid keys: promoted invalid key or split incorrectly");

            assertEquals(1, root.getKeys().size(), "Root has to have 1 key after split");
            assertEquals(expectedRootKey, root.getKeys().getFirst(), "Invalid key promoted to root");
        }

        @Order(2)
        @Test
        @DisplayName("Right inner node split")
        void rightInnerNodeSplit() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 8);
            final var root = tree.getRoot();


            final var expectedLeafKeys = List.of(
                    List.of(2),
                    List.of(5),
                    List.of(7, 9),
                    List.of(12),
                    List.of(23),
                    List.of(38, 39)
            );
            final var expectedInnerKeys = List.of(
                    List.of(5),
                    List.of(12),
                    List.of(38)
            );
            final var expectedRootKeys = List.of(7, 23);

            assertFalse(root.isLeaf(), "Root is leaf");
            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var actualLeafKeys = treeNodes.get(2).stream()
                    .map(Node::getKeys)
                    .toList();
            final var actualInnerKeys = treeNodes.get(1).stream()
                    .map(Node::getKeys)
                    .toList();

            assertEquals(expectedLeafKeys, actualLeafKeys, "Leafs have invalid keys: inserted into invalid leaf or split incorrectly");
            assertEquals(expectedInnerKeys, actualInnerKeys, "Inner nodes have invalid keys: promoted invalid key or split incorrectly");
            assertEquals(expectedRootKeys, root.getKeys(), "Root has invalid keys: invalid key promoted to parent or order is not maintained");
        }

        @Order(3)
        @Test
        @DisplayName("Left inner node split")
        void leftInnerNodeSplit() {
            final var tree = initTreeWithOrderAndKeysInserted(3, 3);
            tree.insert(3);
            tree.insert(2);
            tree.insert(8);
            tree.insert(7);
            tree.insert(4);
            tree.insert(5);
            final var root = tree.getRoot();


            final var expectedLeafKeys = List.of(
                    List.of(2),
                    List.of(3),
                    List.of(4),
                    List.of(5, 7),
                    List.of(8, 9),
                    List.of(12, 23)
            );
            final var expectedInnerKeys = List.of(
                    List.of(3),
                    List.of(5),
                    List.of(12)
            );
            final var expectedRootKeys = List.of(4, 8);

            assertFalse(root.isLeaf(), "Root is leaf");
            final var treeNodes = TreeUtils.collect(tree.getRoot());
            final var actualLeafKeys = treeNodes.get(2).stream()
                    .map(Node::getKeys)
                    .toList();
            final var actualInnerKeys = treeNodes.get(1).stream()
                    .map(Node::getKeys)
                    .toList();

            assertEquals(expectedLeafKeys, actualLeafKeys, "Leafs have invalid keys: inserted into invalid leaf or split incorrectly");
            assertEquals(expectedInnerKeys, actualInnerKeys, "Inner nodes have invalid keys: promoted invalid key or split incorrectly");
            assertEquals(expectedRootKeys, root.getKeys(), "Root has invalid keys: invalid key promoted to parent or order is not maintained");
        }
    }

    @Order(5)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class FullTreeTest {

        @Order(1)
        @ParameterizedTest(name = "Tree order is {0}")
        @MethodSource
        void fullTreeTest(final int order, final Map<Integer, List<List<Integer>>> expectedTreeNodeKeys) {
            final var tree = initTreeWithOrderAndKeysInserted(order);
            final var treeNodeKeys = TreeUtils.collect(tree.getRoot()).entrySet().stream()
                    .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().stream()
                            .map(Node::getKeys)
                            .toList()));
            // To easier comparison in diffs if test fails
            final var sortedExpectedTreeNodeKeys = new TreeMap<>(expectedTreeNodeKeys);

            assertEquals(sortedExpectedTreeNodeKeys, treeNodeKeys);
        }


        public static Stream<Arguments> fullTreeTest() {
            return Stream.of(
                    arguments(3, Map.of(
                            0, List.of(List.of(23)),
                            1, List.of(List.of(7), List.of(39)),
                            2, List.of(List.of(5), List.of(12), List.of(38), List.of(40)),
                            3, List.of(List.of(2), List.of(5), List.of(7, 9), List.of(12), List.of(23), List.of(38), List.of(39), List.of(40, 45))
                    )),
                    arguments(4, Map.of(
                            0, List.of(List.of(38)),
                            1, List.of(List.of(7, 12), List.of(40)),
                            2, List.of(List.of(2, 5), List.of(7, 9), List.of(12, 23), List.of(38, 39), List.of(40, 45))
                    ))
            );
        }
    }

    @Order(6)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Search {

        @Order(1)
        @ParameterizedTest(name = "Tree order is {0}")
        @ValueSource(ints = {3, 4, 5, 6})
        void keysInTree(final int order) {
            final var keysInserted = provideKeys()
                    .toList();
            final var tree = initTreeWithOrderAndKeysInserted(order);

            keysInserted.forEach(key -> assertEquals(key, tree.get(key), "Key %d is not in the tree".formatted(key)));
        }

        @Order(2)
        @ParameterizedTest(name = "Tree order is {0}")
        @ValueSource(ints = {3, 4, 5, 6})
        void keysNotInTree(final int order) {
            final var tree = initTreeWithOrderAndKeysInserted(order);

            final var keysNotInTree = List.of(-100, 1000, 71);

            keysNotInTree.forEach(key -> assertNull(tree.get(key), "Key %d is not supposed to be in tree".formatted(key)));
        }
    }

    private static BPlusTree initTreeWithOrderAndKeysInserted(final int order) {
        final var tree = new BPlusTree(order);
        provideKeys()
                .forEach(tree::insert);

        return tree;
    }

    private static BPlusTree initTreeWithOrderAndKeysInserted(final int order, final int keysToInsert) {
        final var tree = new BPlusTree(order);
        provideKeys().limit(keysToInsert)
                .forEach(tree::insert);

        return tree;
    }

    private static Stream<Integer> provideKeys() {
        return Stream.of(
                12,
                9,
                23,
                5,
                2,
                7,
                38,
                39,
                40,
                45
        );
    }
}