/**
 * @author tian
 */
public class MoreThanHalfNum {

    public static void main(String[] args) {
        int[] result = new int[]{1,2,3,2,2,2,5,4,2};

        int[] result2 = new int[]{2,2,1,1,1,2,2};

        MoreThanHalfNum more = new MoreThanHalfNum();

        System.out.println(more.majorityElement(result2));
    }

    public int majorityElement(int[] nums) {

        int start = 0, end = nums.length - 1;
        int idx = partition(nums, start, end);
        int half = end >> 1;

        while (idx != half) {
            if (idx < half) {
                start = idx + 1;
                idx = partition(nums, start, end);
            } else {
                end = idx - 1;
                idx = partition(nums, start, end);
            }
        }

        return nums[idx];
    }

    public int partition(int[] nums, int start, int end) {
        int small = start - 1;

        int random = start;

        swap(nums, random, end);

        for (int idx = start; idx < end; idx++) {
            if (nums[idx] < nums[end]) {
                small ++;
                if (small != idx) {
                    swap(nums, small, idx);
                }
            }
        }
        small ++;
        swap(nums, small, end);
        return small;
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
