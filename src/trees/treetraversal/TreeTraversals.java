package trees.treetraversal;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversals {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1,
                                                                    new Node<>(2,
                                                                                   new Node<>(4, null,null),
                                                                                   new Node<>(5, null, null)
                                                                                        ),
                                                                    new Node<>(3,
                                                                                   new Node<>(6, null, null),
                                                                                   new Node<>(7, null, null)
                                                                                        )
                                                                );
        TreeTraversals tree = new TreeTraversals();
        tree.bfs(root);
        System.out.println();
        tree.dfs(root);
    }

    public void bfs(Node r) {
        Queue<Node> ns = new LinkedList<>();
        if (r != null)
            ns.offer(r);
        Node n;
        while (!ns.isEmpty()) {
            n = ns.poll();
            if (n.left != null) {
                ns.offer(n.left);
            }
            if (n.right != null) {
                ns.offer(n.right);
            }
            System.out.println(n.value);
        }
    }

    public void dfs(Node n) {
        if (n == null) {
            return;
        }
        System.out.println(n.value);
        dfs(n.left);
        dfs(n.right);
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
