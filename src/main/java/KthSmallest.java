public class KthSmallest {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,5,9},{10,11,13},{12,13,15}};

        KthSmallest kth = new KthSmallest();

        System.out.println(kth.kthSmallest(matrix, 8));

    }

    public int kthSmallest(int[][] matrix, int k) {

        // use binary search method
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n-1][n-1];

        while (start < end) {
            int mid = (end + start) >> 1;

            int count = countMatrix(matrix, mid);

            if (count == k) {
                break;
            } else if (count > k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private int countMatrix(int[][] matrix, int mid) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] <= mid) {
                    count++;
                }else{
                    break;
                }
            }
        }

        return count;
    }
}
