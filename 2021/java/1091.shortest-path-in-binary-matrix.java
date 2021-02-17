class Solution {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) return -1;
        int steps = 1;
        Queue<int[]> queue = new LinkedList<>();
        // start from top-left (0,0) position
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                // already reach to bottom-right, return steps
                if (r == rows - 1 && c == cols - 1) return steps;
                // traverse 8 directions
                for (int[] d : DIRS) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    // check boundries and reset already visited position
                    if (isValid(nr, nc, rows, cols, grid)) {
                        queue.offer(new int[]{nr, nc});
                        grid[nr][nc] = -1;
                    }
                }
                grid[r][c] = -1;
            }
            steps++;
        }
        // not found valid path
        return -1;
    }
    private boolean isValid(int r, int c, int rows, int cols, int[][] grid) {
        return r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 0;
    }
}
