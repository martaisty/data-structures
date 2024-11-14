# B+ Tree

Presentation: [B+ Tree.pdf](./B+%20Tree.pdf)

Visualization:
https://www.cs.usfca.edu/~galles/visualization/BPlusTree.html

## Exercise

Implement B+ tree in package `com.synytsia.bpt`. You can find B+ tree data structure explanation below:

1. Implement nodes: `Node`, `InnerNode`, `LeafNode`.
    1. Add needed fields in each class.
    2. Initialize fields in constructors.
2. Add needed fields in `BPlusTree`.
    1. Initialize fields in constructor.
3. Implement `BPlusTree#getRoot` method.
4. **Add** and implement `BPlusTree#findLeaf` method. It will be used later.
5. Implement `BPlusTree#get` method:
    1. Find correct leaf, reuse `findLeaf` method.
    2. Look up the key in the leaf.
6. Implement `BPlusTree#insert`.
    1. Find correct leaf, reuse `findLeaf` method.
    2. Insert new key in the leaf at the correct position to keep ascending order.
       This implementation **won't support duplicates**: if keys exists in leaf (i.e. tree), don't add it.
    3. No overflow (`keys.size < order`) ➡ END
    4. Overflow (`keys.size == order`):
        1. Split the node evenly by maintaining [tree properties](#properties)
        2. Promote middle key (first one in new node) to the parent.
        3. Repeat steps above until a parent found that need no split.

           > ❗ Inner node split differs from leaf:
           >
           > - its children split
           > - promoted key is **kicked out**

        4. If root splits - treat it as an empty parent

---

## DS breakdown

### Properties

- Tree order: $m$
- Max number of keys in node: $K = m - 1$
- Min number of keys in node (isn't applied to root):
    - Leaf: $\lceil K / 2 \rceil$
    - Inner: $\lfloor K / 2 \rfloor$

### Structure

### Search

### Insertion