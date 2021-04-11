class Solution {
    // DFS + memo
    // for every element, check local longest increase path, compare with global max
    // cache each element result, for better performance
    private final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int max = 1;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] cache = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int currMax = dfs(matrix, r, c, cache);
                max = Math.max(max, currMax);
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int r, int c, int[][] cache) {
        if (cache[r][c] != 0) return cache[r][c];
        int max = 1;
        for (int[] d : DIRS) {
            int nr = d[0] + r;
            int nc = d[1] + c;
            if (!isValid(nr, nc, matrix) || matrix[nr][nc] <= matrix[r][c]) continue;
            int curr = 1 + dfs(matrix, nr, nc, cache);
            max = Math.max(max, curr);
        }
        cache[r][c] = max;
        return max;
        
    }
    private boolean isValid(int r, int c, int[][] matrix) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length;
    }
}
