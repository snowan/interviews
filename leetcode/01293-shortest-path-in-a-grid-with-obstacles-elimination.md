## Problem: 1293. Shortest Path in a Grid with Obstacles Elimination

https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/

You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:


Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:


Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.
 

Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 40
 1 <= k <= m * n
 grid[i][j] is either 0 or 1.
 grid[0][0] == grid[m - 1][n - 1] == 0


## Solution

shortest path -- BFS

**Time Complexity:** O(m*n)

**Space Complexity:** O(m*n)

```python
class Solution:
    def def shortestPath(self, grid: List[List[int]], k: int) -> int:
        if not grid or not grid[0]:
            return 0
        DIRS = [[0,1],[0,-1],[1,0],[-1,0]]
        queue = collections.deque([(0, 0, 0, k)]) # (row, col, steps, k)
        visited = set([0, 0, k])
        rows, cols = len(grid), len(grid[0])
        while queue:
            size = len(queue)
            for _ in range(size):
                r, c, steps, obs = queue.popleft()
                if r == rows - 1 and c == cols - 1:
                    return steps
                for d in DIRS:
                    nr, nc = r + d[0], c + d[1]
                    if nr < 0 or nr >= rows or nc < 0 or nc >= cols:
                        continue
                    if grid[nr][nc] and obs > 0 and (nr, nc, obs - 1) not in visited:
                        visited.add((nr, nc, obs - 1))
                        queue.append((nr, nc, steps + 1, obs - 1))
                    if grid[nr][nc] == 0 and (nr, nc, obs) not in visited:
                        visited.add((nr, nc, obs))
                        queue.append((nr, nc, steps + 1, obs))

        return -1

```
