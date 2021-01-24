class Solution {
    public int[][] diagonalSort(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return mat;
        // map to store r-c (diagonal) list of values
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                map.computeIfAbsent(r - c, pq -> new PriorityQueue<>()).add(mat[r][c]);
            }
        }
        // replace sorted list back to mat
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                mat[r][c] = map.get(r - c).poll();
            }
        }
        return mat;
    }
}
