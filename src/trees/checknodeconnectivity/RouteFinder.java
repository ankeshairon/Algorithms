package trees.checknodeconnectivity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
 */
public class RouteFinder {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2, n1);
        Node n3 = new Node(3, n2);
        RouteFinder network = new RouteFinder();
        System.out.println(network.areConnected(n3, n1));
        System.out.println(network.areConnected(n1, n3));
    }

    public boolean areConnected(Node from, Node to) {
        Queue<Node> q = new LinkedList<>();
        q.add(from);
        Node n;
        while (!q.isEmpty()) {
            n = q.poll();
            if (n.visited) {
                continue;
            }
            if (n.id == to.id) {
                return true;
            }
            n.visited = true;
            q.addAll(n.nbrs);
        }
        return false;
    }
}

class Node {
    int id;
    List<Node> nbrs;
    boolean visited;

    Node(int id, Node... nbrs) {
        this.id = id;
        this.nbrs = Arrays.asList(nbrs);
    }
}