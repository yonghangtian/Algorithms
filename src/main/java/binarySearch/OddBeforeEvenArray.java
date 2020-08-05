package binarySearch;

import java.util.Arrays;

public class OddBeforeEvenArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        OddBeforeEvenArray test = new OddBeforeEvenArray();

        System.out.println(Arrays.toString(test.exchange(arr)));
    }
    public int[] exchange(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        int start = 0, end = nums.length - 1;

        while (start < end) {
            if (nums[start]%2 == 0 && nums[end]%2 == 1) {
                swap(nums, start, end);
                start ++;
                end --;
            } else if (nums[start]%2 == 1) {
                start ++;
            } else if (nums[end]%2 == 0) {
                end --;
            }
        }

        return nums;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
