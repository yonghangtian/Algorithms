import java.util.Comparator;
import java.util.PriorityQueue;

public class TopKthSmallest {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,3,5,2};
        int[] result = new int[3];

        TopKthSmallest kth = new TopKthSmallest();

        result = kth.getLeastNumbers(arr, 3);
    }
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
            return new int[0];
        }

        // achieve a max heap, the top number is the largest number in this heap.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if (priorityQueue.size() != k) {
                priorityQueue.add(arr[i]);
            } else if (arr[i] < priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }

        int[] result = new int[priorityQueue.size()];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll();
        }

        return result;
    }
}
