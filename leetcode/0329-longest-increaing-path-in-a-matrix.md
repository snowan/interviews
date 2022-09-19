## Problem: 329. Longest Increasing Path in a Matrix

https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

 Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Example 3:

Input: matrix = [[1]]
Output: 1
 
Constraints:

 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 200
 0 <= matrix[i][j] <= 231 - 1


## Solutions

#### Solution 1: DFS + memorization

```python
class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        if not matrix or not matrix[0]:
            return 0
        DIRS = [[0,1],[0,-1],[1,0],[-1,0]]
        memo, res = {}, 0
        rows, cols = len(matrix), len(matrix[0])
        def dfs(r, c, memo):
            if (r, c) in memo:
                return memo[(r, c)]
            ans = 1
            for d in DIRS:
                nr, nc = r + d[0], c + d[1]
                if nr < 0 or nr >= rows or nc < 0 or nc >= cols or matrix[nr][nc] <= matric[r][c]:
                    continue
                ans = max(ans, dfs(nr, nc, memo) + 1)
            memo[(r, c)] = ans
            return ans

        for r in range(rows):
            for c in range(cols):
                res = max(res, dfs(r, c, memo))

        return res

```
