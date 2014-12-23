package checktreebalance;

public class CheckTreeBalance {
    public static void main(String[] args) {
        Node<Integer> root1 = new Node<>(1,
                                                            new Node<>(2,
                                                                    new Node<>(4, null,null),
                                                                    new Node<>(5, null, null)
                                                            ),
                                                            new Node<>(3,
                                                                    new Node<>(6, null, null),
                                                                    new Node<>(7, null, null)
                                                            )
                                                    );
        System.out.println(new CheckTreeBalance().isBalanced(root1));
        Node<Integer> root2 = new Node<>(1,
                                                            new Node<>(2,
                                                                    new Node<>(4, null,null),
                                                                    new Node<>(5, null, null)
                                                            ),
                                                            new Node<>(3,
                                                                    new Node<>(6,
                                                                            new Node<>(8,
                                                                                    new Node<>(10, null, null),
                                                                                    new Node<>(11, null, null)
                                                                            ),
                                                                            new Node<>(9, null, null)
                                                                    ),
                                                                    new Node<>(7, null, null)
                                                            )
        );
        System.out.println(new CheckTreeBalance().isBalanced(root2));
        Node<Integer> root3 = new Node<>(1,
                                                    new Node<>(2,
                                                            new Node<>(4, null,null),
                                                            new Node<>(5, null, null)
                                                    ),
                                                    new Node<>(3,
                                                            new Node<>(6,
                                                                    new Node<>(8, null, null),
                                                                    new Node<>(9, null, null)
                                                            ),
                                                            new Node<>(7, null, null)
                                                    )
        );
        System.out.println(new CheckTreeBalance().isBalanced(root3));
    }

    int maxDepth;
    int minDepth;

    public CheckTreeBalance() {
        maxDepth = Integer.MIN_VALUE;
        minDepth = Integer.MAX_VALUE;
    }

    public boolean isBalanced(Node root) {
        if (root == null) {
            return false;
        }
        dfs(root, -1);
        return (maxDepth - minDepth <= 1);

    }

    private void dfs(Node node, int d) {
        ++d;
        if (node.left == null && node.right == null) {
            maxDepth = Math.max(maxDepth, d);
            minDepth = Math.min(minDepth, d);
            return;
        }
        if (node.left != null) {
            dfs(node.left, d);
        }
        if (node.right != null) {
            dfs(node.right, d);
        }
        --d;
    }
}

class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;

    Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
