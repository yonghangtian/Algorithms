package wc207;

import java.util.HashMap;
import java.util.Map;

public class MaxProductPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr1 = new int[][]{{-1,-2,-3},{-2,-3,-3},{-3,-3,-2}};
		int[][] arr2 = new int[][]{{1,-2,1},{1,-2,1},{3,-4,1}};
		int[][] arr3 = new int[][]{{1,3},{0,-4}};
		int[][] arr4 = new int[][]{{1,4,4,0},{-2,0,0,1},{1,-1,1,1}};
		
		MaxProductPath solution = new MaxProductPath();
		
		System.out.println(solution.maxProductPath(arr1));
		System.out.println(solution.maxProductPath(arr2));
		System.out.println(solution.maxProductPath(arr3));
		System.out.println(solution.maxProductPath(arr4));
	}

    public int maxProductPath(int[][] grid) {
        
        Map<String, int[]> state = new HashMap<>();
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        String key ="0 0";
        int[] arr = new int[1]; 
        arr[0] = grid[0][0];
        
        state.put(key, arr);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < rows; i ++){
            for (int j= 0; j < cols; j++) {
                
                String keyInside = String.valueOf(i)+" "+ String.valueOf(j);
                
                if (i == 0 && j == 0) {
                    continue;
                } else if (j == 0) {
                    String prevKey = String.valueOf(i-1)+" "+ String.valueOf(j);
                    
                    int[] arrInside = new int[1];
                    arrInside[0] = state.get(prevKey)[0] * grid[i][j];
                    
                    state.put(keyInside, arrInside);
                } else if (i == 0){
                	String prevKey = String.valueOf(i)+" "+ String.valueOf(j-1);
                    
                    int[] arrInside = new int[1];
                    arrInside[0] = state.get(prevKey)[0] * grid[i][j];
                    
                    state.put(keyInside, arrInside);
                } else {
                    String prevKey1 = String.valueOf(i-1)+" "+ String.valueOf(j);
                    String prevKey2 = String.valueOf(i)+" "+ String.valueOf(j-1);
                    
                    int[] prevArr1 = state.get(prevKey1);
                    int[] prevArr2 = state.get(prevKey2);
                    
                    int[] arrInside = new int[2];
                    
                    int max = 0, min = 0;
                    
                    if (i == 1 && j == 1) {
                        max = Math.max(prevArr1[0] * grid[i][j], prevArr2[0] * grid[i][j]);
                        min = Math.min(prevArr1[0] * grid[i][j], prevArr2[0] * grid[i][j]);
                    } else if (i == 1){
                        max = Math.max(Math.max(prevArr1[0] * grid[i][j], prevArr2[0] * grid[i][j]), prevArr2[1] * grid[i][j]);
                        min = Math.min(Math.min(prevArr1[0] * grid[i][j], prevArr2[0] * grid[i][j]), prevArr2[1] * grid[i][j]);
                    } else if (j == 1){
                        max = Math.max(Math.max(prevArr1[0] * grid[i][j], prevArr1[1] * grid[i][j]), prevArr2[0] * grid[i][j]);
                        min = Math.min(Math.min(prevArr1[0] * grid[i][j], prevArr1[1] * grid[i][j]), prevArr2[0] * grid[i][j]);
                    } else {
                        max = Math.max(Math.max(prevArr1[0] * grid[i][j], prevArr1[1] * grid[i][j]), 
                                       Math.max(prevArr2[0] * grid[i][j], prevArr2[1] * grid[i][j]));
                        min = Math.min(Math.min(prevArr1[0] * grid[i][j], prevArr1[1] * grid[i][j]), 
                                       Math.min(prevArr2[0] * grid[i][j], prevArr2[1] * grid[i][j]));
                    }

                    arrInside[0] = max;
                    arrInside[1] = min;
                    
                    state.put(keyInside, arrInside);
                }
            }
        }
    
        key = String.valueOf(rows-1)+" "+ String.valueOf(cols-1);
        int[] result = state.get(key);
        
        if (result[0] >= 0) {
            return (int) (result[0]%(10000000007L));
        }
        
        return -1;
    }
}
