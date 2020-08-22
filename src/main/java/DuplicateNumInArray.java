public class DuplicateNumInArray {

    public static void main(String[] args) {
        // length = n, while all nums are in [0, n-1].
        int[] numsTest = new int[]{2,3,1,0,2,5,3};
        int[] nums = new int[]{0, 1, 2, 0, 4, 5, 6, 7, 8, 9};

        // length = n+1, while all nums are in [1, n].
        int[] numsThatCannotModify = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 12, 9, 10, 11, 12};
        DuplicateNumInArray d = new DuplicateNumInArray();
        System.out.println(d.findDuplicateNumber(numsTest));
        System.out.println(d.findDuplicateNumberWithoutChangeArray(numsThatCannotModify));
    }

    /**
     * Find duplicate number from an array with length n, while all numbers inside this array are in (0, n-1)
     *
     */
    public int findDuplicateNumber(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i != arr[i]) {
                while (i != arr[i]) {
                    int m = arr[i];

                    if (arr[i] == arr[m]) {
                        return  arr[i];
                    } else {
                        swap(arr, i, m);
                    }
                }
            }
        }

        // to do
        return -1;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Find duplicate num without changing array itself from an array with length (n+1), while all nums are between 1 and n.
     * @param nums
     * @return
     */
    public int findDuplicateNumberWithoutChangeArray(int[] nums) {
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
