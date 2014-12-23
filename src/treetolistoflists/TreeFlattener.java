package treetolistoflists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary search tree, design an algorithm which creates a linked list of all the
 * nodes at each depth (eg, if you have a tree with depth D, you’ll have D linked lists).
 */
public class TreeFlattener {

    public static void main(String[] args) {
        Node root = new Node(1,
                                            new Node(2,
                                                    new Node(4, null, null),
                                                    new Node(5, null, null)
                                            ),
                                            new Node(3,
                                                    new Node(6,
                                                            new Node(8,
                                                                    new Node(10, null, null),
                                                                    new Node(11, null, null)
                                                            ),
                                                            new Node(9, null, null)
                                                    ),
                                                    new Node(7, null, null)
                                            )
                                    );
        final List<List<Node>> flattened = new TreeFlattener().flatten(root);
        System.out.println(flattened);

    }

    public List<List<Node>> flatten(Node r) {
        List<List<Node>> listOfLevels = new ArrayList<>();
        listOfLevels.add(Arrays.asList(r));
        int previousLevelNo = 0;
        LinkedList<Node> nextLevel;

        while (listOfLevels.size() == previousLevelNo + 1) {
            nextLevel = new LinkedList<>();
            for (Node n : listOfLevels.get(previousLevelNo)) {
                if (n.left != null) {
                    nextLevel.add(n.left);
                }
                if (n.right != null) {
                    nextLevel.add(n.right);
                }
            }
            ++previousLevelNo;
            if (nextLevel.size() > 0) {
                listOfLevels.add(nextLevel);
            }
        }
        return listOfLevels;
    }
}

class Node {
    Object value;
    Node left;
    Node right;

    Node(Object value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
