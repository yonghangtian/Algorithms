import java.util.Arrays;
import java.util.Random;

/**
 * @author tian
 */
public class SortingAlgos {
    public static void main(String[] args) {
        SortingAlgos sort = new SortingAlgos();

        //initialize array
        int[] a1 = new int[]{2,1,5,5,8,3,4,9};
        int[] a2 = new int[]{2,1,5,5,8,3,4,9};

        int[] a3 = new int[]{2,2,1,1,1,2,2};
        // quick sort
        System.out.println("Quick Sort");
        sort.quickSort(a3);
        System.out.println(Arrays.toString(a3));

        // quick sort
        System.out.println("Quick Sort");
        sort.quickSort(a1);
        System.out.println(Arrays.toString(a1));

        // merge sort
        System.out.println("Merge Sort");
        sort.mergeSort(a2);
        System.out.println(Arrays.toString(a2));

    }

    /**
     * Merge sort algorithm.
     * The basic idea is to merge two ordered array into one array.
     * @param a2
     */
    private void mergeSort(int[] a2) {
        if (a2 == null || a2.length == 0) {
            return;
        }

        //initialize additional space
        int[] result = new int[a2.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        //merge sort core
        mergeSortCore(a2, result, 0, a2.length - 1);

        //in the merge sort, a2 is re-ordered, no need to do post processing
    }

    private void mergeSortCore(int[] a2, int[] result, int start, int end) {

        if (start >= end) {
            return;
        }

        //use bit shift instead of divide operator.
        int mid = (start + end) >> 1;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;

        mergeSortCore(a2, result, start1, end1);
        mergeSortCore(a2, result, start2, end2);

        int k = start;
        while ((start1 <= end1) && (start2 <= end2)){
            result[k++] = a2[start1] > a2[start2] ? a2[start2++] : a2[start1++];
        }
        while (start1 <= end1){
            result[k++] = a2[start1++];
        }
        while (start2 <= end2){
            result[k++] = a2[start2++];
        }

        for (int i = start; i <= end; i++) {
            a2[i] = result[i];
        }
    }

    /**
     * quick sort algorithm.
     * the core of quick sort is to use partition method.
     * the partition method let nums less than the picked number on the left hand side of the picked number;
     * and let nums no less than the picked number of the right hand side of the picked number.
     * @param array
     */
    private void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSortCore(array,0, array.length-1);
    }

    private void quickSortCore(int[] array, int start, int end){
        if (start == end) {
            return;
        }

        int index = partition(array,start, end);
        if (index > start) {
            quickSortCore(array, start, index - 1);
        }

        if (index < end){
            quickSortCore(array, index + 1, end);
        }

    }

    private int partition(int[] array, int start, int end) {
        int small = start - 1;

        //get a random number between start and end
        int randomIdx = (int) (Math.round(Math.random()*(end - start)) + start);

        //swap randomIdx with end
        swap(array, randomIdx, end);

        //put all value that is smaller than array[randomIdx] on the left side of randomIdx
        for (int index = start; index < end; index ++) {

            if (array[index] < array[end]) {
                small++;
                if (small != index) {
                    swap(array, small, index);
                }
            }
        }

        small ++;

        //move array[randomIdx] back to array[small]
        swap(array, small, end);

        return small;
    }

    public static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
