package lists.nthlastlistelement;

public class NthLastElementOfLinkedList {

    int positionFromEnd;

    public NthLastElementOfLinkedList() {
        this.positionFromEnd = 0;
    }

    public static void main(String[] args) {
        Node n = new Node(5, new Node(9, new Node(3, new Node(1, null))));
        NthLastElementOfLinkedList solution = new NthLastElementOfLinkedList();
        System.out.println(solution.getNthLastElement(n, 2).value);
    }

    Node getNthLastElement(Node node, int n) {
        Node t;
        if (node.next != null) {
            if ((t = getNthLastElement(node.next, n)) != null) {
                return t;
            }
        }
        return ++positionFromEnd == n ? node : null;
    }
}

class Node {
    int value;
    Node next;

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
