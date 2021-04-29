class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        if not obstacleGrid or len(obstacleGrid) == 0:
            return 0
        if obstacleGrid[-1][-1] == 1 or obstacleGrid[0][0] == 1:
            return 0
        rows = len(obstacleGrid)
        cols = len(obstacleGrid[0])
        
        dp = [[0] * cols for r in range(rows)]
        dp[0][0] = 1
        
        for r in range(rows):
            for c in range(cols):
                if obstacleGrid[r][c] == 0:
                    dp[r][c] += dp[r - 1][c] if r >= 1 else 0
                    dp[r][c] += dp[r][c - 1] if c >= 1 else 0
                    
        return dp[-1][-1]
