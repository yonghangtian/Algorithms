import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


//leetcode contest:193 weekly. 5437. 不同整数的最少数目
public class FindLeastNumOfUniqueInts {
    public static void main(String[] args) {
        int[] array = new int[]{2,1,1,3,3,3};
        int k = 3;
        FindLeastNumOfUniqueInts self = new FindLeastNumOfUniqueInts();
        System.out.println(self.findLeastNumOfUniqueInts(array, k));
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (arr.length == k){
            return 0;
        }

        int[] uniqNums = new int[k];
        int checkKey = arr[0];
        int minFreq = Integer.MAX_VALUE;
        Map<Integer, Integer> numsAndFreq = findNumsAndFreq(arr);

        // define a priorityQueue that rank key-value pair using value.
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        //put all ker-value pair into priorityQueue and get the minimum value every time.
        for (Map.Entry<Integer, Integer> entry : numsAndFreq.entrySet()) {
            priorityQueue.add(entry);
        }

        if(k == 0){
            return numsAndFreq.size();
        }
        // always remove from the minimun value
        for (int i = 0; i < k; i++){
            if (i == 0 || minFreq == 0){
                Map.Entry<Integer, Integer> min = priorityQueue.poll();
                minFreq = min.getValue();
                checkKey = min.getKey();
            }
            minFreq -= 1;
            reduceKeyFreq(numsAndFreq, checkKey);
            uniqNums[i] = numsAndFreq.size();
        }

        return uniqNums[k-1];
    }

    private static void reduceKeyFreq(Map<Integer, Integer> map, int key){
        int freq = map.get(key);
        if (freq == 1){
            map.remove(key);
        }else{
            map.put(key, freq -1);
        }
    }

    private static Map<Integer, Integer> findNumsAndFreq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for( int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
            }else{
                map.put(arr[i], 1);
            }
        }
        return map;
    }
}
