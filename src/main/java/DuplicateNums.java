public class DuplicateNums {

    public static void main(String[] args) {
        //int[] nums = new int[]{0, 1, 2, 0, 4, 5, 6, 7, 8, 9};
        //int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 12, 9, 10, 11, 12};
        DuplicateNums d = new DuplicateNums();
        System.out.println(d.findRepeatNumber(nums));
    }

    /**
     * Find duplicate num without changing array itself from an array with length (n+1), while all nums are between 1 and n.
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        // to implement a way not to edit the array itself.
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int middle = (end - start)/2 + start;

            boolean firstHalfBigger = timesInArrayBiggerThanLength(nums, start, middle);
            boolean secondHalfBigger = timesInArrayBiggerThanLength(nums, middle+1, end);

            if (start == end){
                if (firstHalfBigger) {
                    return start;
                }else{
                    break;
                }
            }

            while (!firstHalfBigger && !secondHalfBigger){
                middle --;
                firstHalfBigger = timesInArrayBiggerThanLength(nums, start, middle);
                secondHalfBigger = timesInArrayBiggerThanLength(nums, middle+1, end);
            }

            if (firstHalfBigger) {
                end = middle;
            }else{
                start = middle + 1;
            }
        }

        return -1;
    }

    private static boolean timesInArrayBiggerThanLength(int[] nums, int start, int end) {
        int diff = end - start + 1;
        int count = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] >= start && nums[i] <= end) {
                count ++;
            }
        }
        return count > diff ? true:false;
    }
}
