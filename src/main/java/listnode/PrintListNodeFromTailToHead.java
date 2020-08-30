package listnode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static listnode.ListNodeQestions.convertArrayToListNode;

/**
 * @author tian
 */
public class PrintListNodeFromTailToHead {
    public static void main(String[] args) {
        ListNode head1 = convertArrayToListNode(new int[]{1,2,3,4,5});

        PrintListNodeFromTailToHead print = new PrintListNodeFromTailToHead();

        System.out.println(print.reversePrintUseStack(head1).toString());

        System.out.println(print.reversePrint(head1).toString());
    }

    public int[] reversePrintUseStack(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();

        while (head != null) {
            // Remember deque always operate with its first element.
            stack.addFirst(head.val);
            head = head.next;
        }

        int[] arr = new int[stack.size()];
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = stack.removeFirst();
        }

        return arr;
    }

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();

        reversePrintCore(head, list);

        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public void reversePrintCore(ListNode head, List<Integer> list) {
        if (head == null) {
            return ;
        }

        reversePrintCore(head.next, list);
        list.add(head.val);
    }
}
