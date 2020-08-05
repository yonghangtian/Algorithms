import java.util.Deque;
import java.util.LinkedList;

class MaxQueue {
    Deque<Integer> queue;
    Deque<Integer> maxQueue;
    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }

        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        queue.offerLast(value);

        int count = 0;
        while (!maxQueue.isEmpty() && value > maxQueue.peekLast()) {
            maxQueue.pollLast();
            count ++;
        }

        while (count > 0) {
            maxQueue.offerLast(value);
            count--;
        }

        maxQueue.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int result = queue.pollFirst();
        maxQueue.pollFirst();

        return result;
    }

    public static void main(String[] args) {
        MaxQueue max = new MaxQueue();

        max.push_back(1);
        max.push_back(2);
        max.push_back(-2);
        max.push_back(3);
        System.out.println(max.pop_front());
        System.out.println(max.pop_front());


    }
}