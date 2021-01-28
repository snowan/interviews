class Solution {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minimumEffortPath(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) return 0;
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] dp = new int[rows][cols];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        // int[3] = {r, c, effort}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // start from top left (0, 0) with 0 effort {0, 0, 0}
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            // already bottom right, break
            if (r == rows - 1 && c == cols - 1) break;
            // iterate 4 directions
            for (int[] d : DIRS) {
                int newR = r + d[0];
                int newC = c + d[1];
                if (!isValid(rows, cols, newR, newC)) continue;
                int currMin = Math.max(Math.abs(heights[newR][newC] - heights[r][c]), curr[2]);
                if (currMin < dp[newR][newC]) {
                    // System.out.println(newR + ", " + newC + ", " + currMin);
                    dp[newR][newC] = currMin;
                    pq.offer(new int[]{newR, newC, currMin});
                }
                
            }
        }
        return dp[rows - 1][cols - 1];
    }
    private boolean isValid(int rows, int cols, int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}
