class Solution {
    // dp: 
    // dp[i][j] -- represents max path can reach to cell (i,j)
    // 1. for any (i,j) == 1 (obstacle), reset dp[i][j] = 0
    // 2. for any(i,j) == 0, dp[i][j] = dp[i - 1][j] + dp[i][j - 1] (only right and down path)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // init first row
        for (int c = 0; c < n; c++) {
            if (obstacleGrid[0][c] == 1) break;
            dp[0][c] = 1;
        }
        // first col
        for (int r = 0; r < m; r++) {
            if (obstacleGrid[r][0] == 1) break;
            dp[r][0] = 1;
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[r][c] = 0;
                } else {
                    dp[r][c] = dp[r][c - 1] + dp[r - 1][c];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
