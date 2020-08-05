package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tian
 */
public class FindContinuousSeq {
    public static void main(String[] args) {
        int sum = 9;

        List<int[]> intArrList = new ArrayList<>();

        FindContinuousSeq find = new FindContinuousSeq();

        int[][] arr = find.findContinuousSequence(sum);

        System.out.println(Arrays.toString(arr));
    }
    public int[][] findContinuousSequence(int sum) {
        List<List<Integer>> list = new ArrayList<>();

        if (sum < 3) {
            return new int[0][0];
        }

        int start = 1, end = 2;

        int middle = sum >> 1;

        while (start <= middle) {
            int curr = sumOfNumber(start, end);
            if ( curr == sum) {
                list.add(convertList(start, end));
                start++;
            } else if (curr > sum) {
                start ++;
            } else {
                end ++;
            }
        }

        return convertListToArray(list);
    }

    public int[][] convertListToArray(List<List<Integer>> list) {
        int[][] arr = new int[list.size()][];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[list.get(i).size()];

            for (int j = 0; j < arr[i].length; j ++) {
                arr[i][j] = list.get(i).get(j);
            }
        }

        return arr;
    }

    public int sumOfNumber(int start, int end) {
        int result = 0;

        for (int i = start; i <= end; i++) {
            result += i;
        }

        return result;
    }

    public List<Integer> convertList(int start, int end) {
        List<Integer> list = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        return list;
    }
}
