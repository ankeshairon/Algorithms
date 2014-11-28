package countvisiblenodes;

import java.util.Stack;

/**
 * In a binary tree, if in the path from root to the node A, there is no node with greater value than A’s, this node A is visible.
 * We need to count the number of visible nodes in a binary tree.
 */
public class CountVisibleNodes {

    Stack<Integer> maxs;

    public CountVisibleNodes() {
        maxs = new Stack<Integer>();
    }

    public static void main(String[] args) {
//    Tree t = new Tree(5, new Tree(3, new Tree(20, null, null), new Tree(21, null, null)), new Tree(10, new Tree(1, null, null), null));
        Tree t = new Tree(
                8,
                new Tree(
                        2,
                        new Tree(8, null, null),
                        new Tree(7, null, null)
                ),
                new Tree(
                        6,
                        null,
                        null
                )
        );

        System.out.println(new CountVisibleNodes().getVisibleNodeCount(t));
    }

    public int getVisibleNodeCount(Tree tree) {
        int count = 0;
        if (tree == null) {
            return count;
        }

        if (maxs.size() == 0 || tree.getValue() >= maxs.peek()) {
            maxs.push(tree.getValue());
            ++count;
        }

        count = count + getVisibleNodeCount(tree.getLeft());
        count = count + getVisibleNodeCount(tree.getRight());

        if (maxs.peek() == tree.getValue()) {
            maxs.pop();
        }
        return count;
    }
}
