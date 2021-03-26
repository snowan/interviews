
// BFS or DFS 

class Solution {
    private static final int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
       
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            pacific[i][0] = true;
            atlantic[i][m - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            pacific[0][i] = true;
            atlantic[n - 1][i] = true;
        }
        for (int i = 0; i < n; i++) {
            fill(pacific, matrix, i, 0);
            fill(atlantic, matrix, i, m - 1);
        }
        for (int j = 0; j < m; j++) {
            fill(pacific, matrix, 0, j);
            fill(atlantic, matrix, n - 1, j);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    private void fill(boolean[][] grid, int[][] matrix, int i, int j) {
        grid[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d][0];
            int y = j + dirs[d][1];
            if (isValid(x, y, i, j, matrix) && !grid[x][y]) {
                fill(grid, matrix, x, y);
            }
        }
    }
    private boolean isValid(int x, int y, int i, int j, int[][] matrix) {
        if (x >= 0 && x < matrix.length && 
        y >= 0 && y < matrix[0].length && 
        matrix[x][y] >= matrix[i][j]) {
            return true;
        }
        return false;
    }
}
