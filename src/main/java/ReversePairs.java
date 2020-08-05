public class ReversePairs {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 5, 6, 4};

        ReversePairs pair = new ReversePairs();

        System.out.println(pair.reversePairs(nums));
    }

    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int[] copy = new int[nums.length];

        int count = reversePairsCore(nums, copy, 0, nums.length - 1);

        return count;
    }

    public int reversePairsCore(int[] nums, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = nums[start];
            return 0;
        }

        int mid = (start + end) >> 1;

        int left = reversePairsCore(nums, copy, start, mid);
        int right = reversePairsCore(nums, copy, mid + 1, end);
        int count = left + right;
        int p1 = mid, p2 = end, backup = end;

        while (p1 >= start && p2 >= mid+1) {
            if (nums[p1] > nums[p2]) {
                copy[backup --] = nums[p1 --];
                // from nums[mid+1] to nums[p2] can construct a reversePair with nums[p1].
                count += p2 - (mid + 1) + 1;
            } else {
                copy[backup --] = nums[p2 --];
            }
        }

        while (p1 >= start) {
            copy[backup --] = nums[p1 --];
        }

        while (p2 >= mid + 1) {
            copy[backup --] = nums[p2 --];
        }

        // copy back
        for (int i = start; i <= end; i++) {
            nums[i] = copy[i];
        }

        return count;
    }
}
