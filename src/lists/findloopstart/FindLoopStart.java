package lists.findloopstart;

/**
 * Given a circular linked list, implement an algorithm which returns node at the begin-
 * ning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node’s next pointer points to an
 * earlier node, so as to make a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C -> D -> E -> C [the same C as earlier]
 * Output: C
 */
public class FindLoopStart {
    public static void main(String[] args) {
        final Node lastOfNodes = new Node(6, null);
        final Node corruptNode = new Node(4, new Node(5, lastOfNodes));
        final Node n = new Node(1, new Node(2, new Node(3, corruptNode)));
        lastOfNodes.next = corruptNode;
        final int corruptNodeValue = findLoopStart(n).value;
        assert(4 == corruptNodeValue);
        System.out.println(corruptNodeValue);
    }

    private static Node findLoopStart(Node n) {
        Node f = n.next.next;
        Node s = n.next;

        while (f.value != s.value) {
            f = f.next.next;
            s = s.next;
        }

        f = n;
        while (f.value != s.value) {
            f = f.next;
            s = s.next;
        }
        return f;

    }
}

class Node {
    int value;
    Node next;

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}