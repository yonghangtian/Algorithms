package listnode;

import java.util.Deque;
import java.util.LinkedList;

public class ListNodeQestions {

    public static void main(String[] args) {
        ListNodeQestions algo = new ListNodeQestions();
        //[1,2,3,4,5]
        ListNode head1 = convertArrayToListNode(new int[]{1,2,3,4,5});
        ListNode head2 = convertArrayToListNode(new int[]{1,1,2,3,3,4,5,5});
        ListNode head3 = convertArrayToListNode(new int[]{1,2,3,4});
        ListNode head4 = convertArrayToListNode(new int[]{1,2,3,4,5,6,7});

        ListNode head5 = convertArrayToListNode(new int[]{1,3,5,7,8});
        ListNode head6 = convertArrayToListNode(new int[]{2,4,6,8,9});

        //make head4 a node list with loop
        // 1->2->3->[4->5->6->7->4]
        head4.next.next.next.next.next.next.next = head4.next.next.next;
        //printNodeList(head4);

        System.out.println("Current Node List: ");
        printNodeList(head1);
        printNodeList(head2);

        // reverse ListNode method
        head1 = algo.reverseListUsingStack(head1);
        System.out.println("Reversed Node List: ");
        printNodeList(head1);
        head1 = algo.reverseList(head1);
        System.out.println("Reversed node list again:");
        printNodeList(head1);

        // delete node in O(1) time
        ListNode toBeDeleted = head1.next.next.next;
        toBeDeleted.next = head1.next.next.next.next;
        head1 = algo.deleteNode(head1, toBeDeleted);
        printNodeList(head1);

        // delete replicate node in node list
        head2 = algo.deleteReplicateNode(head2);
        printNodeList(head2);

        // the kth node in reverse order
        int k = 5;
        ListNode kthReverse = algo.kthNodeInReverseOrder(head3, k);
        System.out.println(kthReverse.val);

        // detect loop in node list and return the entry of loop
        ListNode entryOfLoop = algo.entryNodeOfLoop(head4);
        System.out.println(entryOfLoop.val);

        // merge two ranked list node
        ListNode mergedHead1 = algo.recursiveMergeTwoRankedList(head5, head6);
        System.out.println("Recursive way of merging list node");
        printNodeList(mergedHead1);

        ListNode mergedHead2 = algo.iterateMergeTwoRankedList(head5, head6);
        System.out.println("Iterate way of merging list node");
        printNodeList(mergedHead2);
    }

    /**
     * Iterate way: Merge two ranked node list and keep the merge list ranked.
     * @param head5
     * @param head6
     * @return merged Head
     */
    private ListNode iterateMergeTwoRankedList(ListNode head5, ListNode head6) {

        if (head5 == null) {
            return head6;
        } else if (head6 == null) {
            return head5;
        }

        ListNode mergedHead = null;
        ListNode node = mergedHead;

        while (head5 != null && head6 != null) {
            node = head5.val <= head6.val ? head5 : head6;

            if (mergedHead == null) {
                mergedHead = node;
            }

            node = node.next;
            head5 = head5.next;
            head6 = head6.next;
        }

        if (head5 == null) {
            node = head6;
        }else if (head6 == null) {
            node = head5;
        }

        return mergedHead;
    }

    /**
     * Recursive way: Merge two ranked node list and keep the merge list ranked.
     * @param head5
     * @param head6
     * @return merged head
     */
    private ListNode recursiveMergeTwoRankedList(ListNode head5, ListNode head6) {

        if (head5 == null) {
            return head6;
        } else if (head6 == null) {
            return head5;
        }

        ListNode mergedHead = head5.val <= head6.val ? head5 : head6;

        if (head5.val <= head6.val) {
            mergedHead.next = recursiveMergeTwoRankedList(head5.next, head6);
        }else {
            mergedHead.next = recursiveMergeTwoRankedList(head5, head6.next);
        }

        return mergedHead;
    }

    /**
     * Check whether there has loop or not; if there is, return the entry node of loop, if no, return null.
     * @param head4
     * @return entry of loop
     */
    private ListNode entryNodeOfLoop(ListNode head4) {
        ListNode nodeInLoop = this.haveLoop(head4);

        if (nodeInLoop == null) {
            System.out.println("There is no loop in this node list!");
            return null;
        }

        int length = checkLoopLength(nodeInLoop);

        ListNode fast = head4;
        ListNode slow = head4;

        while (length > 0) {
            fast = fast.next;
            length --;
        }

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * Check the length of loop inside a node list that has loop.
     * @param node, which is a node in the node loop.
     * @return length of loop, num of nodes inside the loop.
     */
    private int checkLoopLength(ListNode node) {
        ListNode node1 = node.next;

        int length = 1;

        while (node1 != node) {
            node1 = node1.next;

            length++;
        }

        return length;
    }

    /**
     * Check whether there is loop in the node list.
     * @param head4, the head node of node list
     * @return node in loop if there is loop; null if there is no loop
     */
    private ListNode haveLoop(ListNode head4) {
        if (head4 == null || head4.next == null) {
            return null;
        }

        ListNode fast = head4;
        ListNode slow = head4;

        while (fast.next != null && fast.next.next !=null) {
            fast = fast.next.next;

            slow = slow.next;

            if (fast == slow) {
                return fast;
            }
        }

        return null;
    }

    /**
     * The kth node in reverse order.
     * @param head
     * @param k
     * @return the kth node
     */
    private ListNode kthNodeInReverseOrder(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }

        ListNode node = head;
        for (int i = 1; i <= k-1; i++) {
            if (node.next != null) {
                node = node.next;
            } else {
                return null;
            }
        }

        ListNode kthNodeInReverse = head;

        while (node.next != null) {
            kthNodeInReverse = kthNodeInReverse.next;
            node = node.next;
        }

        return kthNodeInReverse;
    }

    /**
     * Delete replicate nodes in a RANKED node list
     * I.E.:
     *  Before: 1->2->3->3->4->4->5
     *  After: 1->2->5
     * @param head
     * @return head1
     */
    private ListNode deleteReplicateNode(ListNode head) {

        if (head == null || head.next == null) {
            System.out.println("Head is null or there is only one node");
            return head;
        }

        ListNode preNode = null;
        ListNode node = head;
        while (node != null) {

            ListNode nextNode = node.next;
            boolean deleteFlag = false;
            if (nextNode != null && node.val == nextNode.val) {
                deleteFlag = true;
            }

            if (!deleteFlag) {
                preNode = node;
            } else {
                int value = node.val;
                ListNode toBeDeleted = node;

                while (toBeDeleted != null && toBeDeleted.val == value) {
                    nextNode = toBeDeleted.next;

                    toBeDeleted.next = null;

                    toBeDeleted = nextNode;
                }

                // when old head node is duplicated, we need to assign the new head.
                if (preNode == null) {
                    head = nextNode;
                } else {
                    // use preNode to connect these non-duplicate nodes.
                    preNode.next = nextNode;
                }
            }

            node = nextNode;
        }

        return head;
    }

    /**
     * delete node in O(1) time
     * @param toBeDeleted, we need to make sure this node exist in the list.
     * @return new head
     */
    private ListNode deleteNode(ListNode head, ListNode toBeDeleted) {
        if (toBeDeleted == null || head == null) {
            System.out.println("At least one of input nodes is null");
            return null;
        }

        ListNode node = null;
        if (head == toBeDeleted) {
            node = head;
            head = node.next;
            node.next = null;
        } else if (toBeDeleted.next == null){
            node = head;
            while (node.next != toBeDeleted) {
                node = node.next;
            }
            node.next = null;
        } else {
            node = toBeDeleted.next;
            toBeDeleted.val = node.val;
            toBeDeleted.next = node.next;
            node.next = null;
        }

        return head;
    }

    /**
     *
     * @param head, head node for node list
     * @return head, new head for reversed node list
     */
    public ListNode reverseListUsingStack(ListNode head) {
        //check head
        if (head == null || head.next == null) {
            return head;
        }

        //push to stack(deque)
        ListNode node = head;
        Deque<ListNode> stack = new LinkedList<ListNode>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        //pop from stack(deque)
        ListNode prev = null;
        node = null;
        while (stack.size() != 0) {
            if (prev == null){
                prev = stack.pop();
                node = prev;
                head = prev;
            }else{
                node = stack.pop();
                prev.next = node;
                prev = node;
            }
        }
        node.next = null;

        return head;
    }

    /**
     *  Reverse node list without additional space
     * @param head
     * @return new head
     */
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode now = head;
        ListNode next = now.next;
        ListNode prev = null;

        while (next != null) {
            now.next = prev;

            // move to next node
            prev = now;
            now = next;
            next = next.next;
        }
        now.next = prev;
        return now;
    }


    /**
     *  Print all nodes in list node.
     * @param head
     */
    private static void printNodeList(ListNode head) {
        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.val);
            sb.append("->");
            head = head.next;
        }
        sb.append("null");

        System.out.println(sb.toString());
    }

    /**
     *
     */
    public static ListNode convertArrayToListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode node = null;
        ListNode prev = head;
        for (int i = 1; i < array.length; i++) {

            node = new ListNode(array[i]);
            prev.next = node;
            prev = node;
            node = node.next;
        }

        return head;
    }
}

