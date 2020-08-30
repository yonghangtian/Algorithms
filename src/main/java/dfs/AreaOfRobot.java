package dfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tian
 */
public class AreaOfRobot {

    public static void main(String[] args) {
        int m = 3, n = 2, k = 1;

        System.out.println(new AreaOfRobot().movingCountDfs(m,n,k));
        System.out.println(new AreaOfRobot().movingCountBfs(m,n,k));
    }

    /**
     * DFS way and reachable cut leaves
     * @param m, row num
     * @param n, col num
     * @param k, target
     * @return total reachable points
     */
    private int movingCountDfs(int m, int n, int k) {
        int[][] visited = new int[m][n];

        return movingCountDfsCore(0,0,m,n,k,visited);
    }

    private int movingCountDfsCore(int i, int j, int m, int n, int k, int[][] visited) {

        if (i >= m || j >= n || visited[i][j] == 1 || countDigits(i, j) > k) {
            return 0;
        }

        visited[i][j] = 1;

        return 1 + movingCountDfsCore(i+1, j, m, n, k, visited) + movingCountDfsCore(i,j+1, m, n, k, visited);
    }

    private int movingCountBfs(int m, int n, int k) {
        int[][] visited = new int[m][n];
        int res = 0;

        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        while (!queue.isEmpty()) {
            int[] index = queue.removeFirst();

            int i = index[0], j = index[1];
            if (i < m && j < n && countDigits(i,j) <= k && visited[i][j] == 0){
                visited[i][j] = 1;
                res++;

                queue.addLast(new int[]{i+1, j});
                queue.addLast(new int[]{i, j+1});
            }
        }
        return res;
    }

    public int countDigits(int m, int n) {
        int result = 0;

        while (m != 0) {
            result += m%10;
            m /= 10;
        }

        while (n != 0) {
            result += n%10;
            n /= 10;
        }

        return result;
    }
}
