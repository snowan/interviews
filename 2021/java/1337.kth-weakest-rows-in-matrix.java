class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || mat.length == 0 || mat[0].length == 0 || k == 0) return new int[0];
        // {i, j} -> ith row has j 1s (soliders )
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? (b[0] - a[0]) : (b[1] - a[1]));
        int rows = mat.length;
        for (int i = 0; i < rows; i++) {
            int ones = 0;
            for (int m : mat[i]) {
                if (m == 0) break;
                ones++;
            }
            pq.offer(new int[]{i, ones});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[k];
        int idx = k - 1;
        while (!pq.isEmpty()) {
            res[idx--] = pq.poll()[0];
        }
        return res;
    }
}
