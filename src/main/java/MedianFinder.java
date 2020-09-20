import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();

//        // test minheap and maxheap
//        finder.minHeap.add(1);
//        finder.minHeap.add(2);
//        finder.minHeap.add(3);
//
//        finder.maxHeap.add(1);
//        finder.maxHeap.add(2);
//        finder.maxHeap.add(3);
//        finder.maxHeap.poll();
//        finder.maxHeap.poll();

        finder.addNum(1);
        finder.addNum(2);

        System.out.println(finder.findMedian());

        finder.addNum(3);

        System.out.println(finder.findMedian());
    }

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    boolean odd;

    public MedianFinder() {
        // Maximum heap, the value of each node is less than or equal to the value of its parent, with the maximum-value element at the root
        maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });

        // Minimum heap, the value of each node is greater than or equal to the value of its parent, with the minimum-value element at the root.
        minHeap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        });

        // indicate the next coming number's order in the stream
        odd = true;
    }

    public void addNum(int num) {
        if (odd) {
            if (maxHeap.size() > 0 && num < maxHeap.peek()) {
                int temp = num;
                maxHeap.add(temp);
                num = maxHeap.poll();
            }
            minHeap.add(num);
        } else {
            if (minHeap.size() > 0 && num > minHeap.peek()) {
                int temp = num;
                minHeap.add(temp);
                num = minHeap.poll();
            }
            maxHeap.add(num);
        }

        odd = !odd;
    }

    public double findMedian() {
        double median = 0.0;

        if (odd) {
            median = (minHeap.peek() + maxHeap.peek())/2.0;
        } else {
            median = minHeap.peek();
        }

        return median;
    }
}
