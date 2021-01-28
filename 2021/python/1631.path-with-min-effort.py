"""
1631. Path With Minimum Effort

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Example 1:

Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:

Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:

Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.


Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
"""
def minimumEffortPath(self, heights: List[List[int]]) -> int:
    if not heights or len(heights) == 0 or len(heights[0]) == 0:
        return 0
    rows, cols = len(heights), len(heights[0])
    dp = [[sys.maxsize for r in range(cols)] for c in range(rows)]
    # print(dp)
    # init top left with effort 0
    dp[0][0] = 0
    # heap keep track of r, c, effort, init with (r, c dis) -> (0,0,0)
    minHeap[(0, 0, 0)]
    # 4 directions
    dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    while minHeap:
        r, c, dis = heapq.heappop(minHeap)
        # already bottom right
        if r == rows - 1 and c == cols - 1:
            break
        # iterate 4 directions
        for d in dirs:
            nr = r + d[0]
            nc = c + d[1]
            # check boundry
            if nr >= 0 and nr < rows and nc >= 0 and nc < cols:
                diff = max(abs(heights[r][c] - heights[nr][nc]), dis)
                if diff < dp[nr][nc]:
                    heapq.heappush(minHeap, (nr, nc, diff))
                    dp[nr][nc] = diff
    return dp[rows - 1][cols - 1]

