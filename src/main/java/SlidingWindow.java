import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,-1,-3,5,3,6,7};

        SlidingWindow win = new SlidingWindow();

        System.out.println(Arrays.toString(win.maxSlidingWindow(arr, 3)));
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> maxQueue = new LinkedList<>();

        int start = 0;
        int[] res = new int[nums.length - k + 1];
        while (start < k) {
            while (!maxQueue.isEmpty() && nums[start] > nums[maxQueue.peekLast()]) {
                maxQueue.pollLast();
            }
            maxQueue.offerLast(start);
            start ++;
        }

        res[0] = nums[maxQueue.peekFirst()];

        for (int i = k; i < nums.length; i ++){
            if (i - k == maxQueue.peekFirst()) {
                maxQueue.pollFirst();
            }

            while (!maxQueue.isEmpty() && nums[i] > nums[maxQueue.peekLast()]) {
                maxQueue.pollLast();
            }

            maxQueue.offerLast(i);
            res[i - k + 1] = nums[maxQueue.peekFirst()];
        }

        return res;
    }
}
