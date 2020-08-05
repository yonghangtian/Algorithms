package binarySearch;

/**
 * @author tian
 */
public class FindNumInRankedArray {

    public static void main(String[] args) {
        FindNumInRankedArray find = new FindNumInRankedArray();

        // First, count the occurrence of a number in ranked query
        int[] arr1 = new int[]{5,7,7,8,8,10};
        int tar1 = 8;

        int result1 = find.searchOccurrence(arr1, tar1);
        System.out.printf("Find occurrence of a number in ranked query: %d appears %d times", tar1, result1);
        System.out.println();
        /*
        一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
         */
        int[] arr2 = new int[]{0,1,2,3,4,5,6,7,9};
        int result2 = find.searchMissingNum(arr2);
        System.out.println("The missing number is " + result2);

        /*
         假设一个单调递增的数组里的每个元素都是整数且是唯一的。请实现一个函数，找出数组中任意一个数值等于其下标的元素。
         例如在数组【-3，-1，1，3，5】中，数字3和它的下标相等。
        */
        int[] arr3 = new int[]{-3,-1,1,3,5};
        int result3 = find.searchNumEqualsIdx(arr3);
        System.out.println("The number that is equal to it's index is " + result3);
    }

    private int searchNumEqualsIdx(int[] arr3) {
        if (arr3.length == 0) {
            return 0;
        }

        int start = 0, end = arr3.length - 1;

        while (start <= end) {
            int mid = (start + end) >> 1;

            if (mid == arr3[mid]) {
                return mid;
            } else if (mid > arr3[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    private int searchMissingNum(int[] arr2) {
        if (arr2.length == 0) {
            return -1;
        }

        int start = 0, end = arr2.length - 1;
        int result = -1;
        while (start <= end) {
            int mid = (start + end) >> 1;

            if (mid < arr2[mid]) {
                if (mid > start && mid - 1 == arr2[mid - 1] || mid == start) {
                    result = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            } else {
                start = mid + 1;
            }
        }

        // if the array looks good, then certainly the biggest number (n-1) is missing.
        if (result == -1) {
            return arr2.length;
        }

        return result;
    }

    private int searchOccurrence(int[] arr1, int tar1) {
        if (arr1.length == 0) {
            return 0;
        }

        int start = 0, end = arr1.length-1;

        // find the first index of tar1
        int firstK = -1;
        while (start <= end) {
            int mid = (start + end) >> 1;

            if (arr1[mid] == tar1) {
                if (mid > start && arr1[mid-1] != tar1 || mid == start) {
                    firstK = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            } else if (arr1[mid] > tar1){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }

        if (firstK == -1) {
            return 0;
        }

        //find the least index of tar1
        start = 0;
        end = arr1.length-1;
        int leastK = -1;
        while (start <= end) {
            int mid = (start + end) >> 1;

            if (arr1[mid] == tar1) {
                if (mid < end && arr1[mid+1] != tar1 || mid == end) {
                    leastK = mid;
                    break;
                } else {
                    start = mid + 1;
                }
            } else if (arr1[mid] > tar1){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }

        return leastK - firstK + 1;
    }


}
