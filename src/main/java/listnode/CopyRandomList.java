package listnode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tian
 */
public class CopyRandomList {
    public static void main(String[] args) {
        CopyRandomList copy = new CopyRandomList();


    }

    /**
     * O(n^2) time, no additional space.
     * @param head
     * @return
     */
    public Node copyRandomListWithN2Time(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;
        Node newHead = null;
        Node newNode = null;

        // copy next chain
        while (node != null) {
            Node temp = new Node(node.val);

            if (newHead == null) {
                newHead = temp;
                newNode = temp;
            } else {
                newNode.next = temp;
                newNode = newNode.next;
            }

            node = node.next;
        }

        node = head;
        newNode = newHead;
        //copy random chain
        while (node != null) {
            Node random = node.random;
            Node newRandom = findRandomNode(head, random, newHead);

            newNode.random = newRandom;

            newNode = newNode.next;
            node = node.next;
        }

        return newHead;
    }

    private Node findRandomNode(Node head, Node random, Node newHead) {
        Node newRandom = null;

        Node node = head;
        Node newNode = newHead;

        while (node != null) {
            if (node.equals(random)) {
                newRandom = newNode;
                break;
            }

            node = node.next;
            newNode = newNode.next;
        }

        return newRandom;
    }

    /**
     * O(n) space, O(n) time
     * @param head
     * @return
     */
    public Node copyRandomListWithAdditionSpace(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;
        Node newHead = null;
        Node newNode = null;

        Map<Node, Node> nodeMap = new HashMap<>();

        // copy next chain
        while (node != null) {
            Node temp = new Node(node.val);

            if (newHead == null) {
                newHead = temp;
                newNode = temp;
            } else {
                newNode.next = temp;
                newNode = newNode.next;
            }

            nodeMap.put(node, temp);

            node = node.next;

        }

        node = head;
        newNode = newHead;
        //copy random chain
        while (node != null) {
            Node random = node.random;
            Node newRandom = nodeMap.get(random);

            newNode.random = newRandom;

            newNode = newNode.next;
            node = node.next;

        }

        return newHead;
    }

    /**
     * O(n) time, no additional space
     * @param head
     * @return
     */
    public Node copyRandomListSmartest(Node head) {
        if (head == null) {
            return null;
        }

        Node newHead = null;
        Node newNode = null;
        Node node = head;

        while (node != null) {
            Node temp = new Node(node.val);

            if (newHead == null) {
                newHead = temp;
            }

            Node tempNext = node.next;
            node.next = temp;
            temp.next = tempNext;

            node = node.next.next;
        }

        node = head;
        while (node != null) {
            Node random = node.random;

            newNode = node.next;
            newNode.random = random.next;

            node = node.next.next;
        }

        node = head;
        newNode = newHead;

        while (newNode != null) {
            node.next = newNode.next;
            node = node.next;
            if (node == null) {
                newNode.next = null;
            }else {
                newNode.next = node.next;
            }
            newNode = newNode.next;
        }

        return newHead;
    }
}

