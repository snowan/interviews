class Solution {
    // LIS 
    public int minOperations(int[] target, int[] arr) {
        // target is distinct
        Map<Integer, Integer> map = new HashMap<>();
        int len = target.length;
        for (int i = 0; i < len; i++) map.put(target[i], i);
        int size = 0;
        int[] piles = new int[len];
        for (int a : arr) {
            if (!map.containsKey(a)) continue;
            int idx = binarySearch(piles, size, map.get(a));
            piles[idx] = map.get(a);
            if (idx == size) size++;
        }
        return len - size;
    }
    private int binarySearch(int[] piles, int size, int target) {
        int l = 0, r = size; 
        while (l < r) {
            int m = l + (r - l) / 2;
            if (piles[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
    
    // LCS 
    // DP LTE (MTE)
    // public int minOperations(int[] target, int[] arr) {
    //     if (target == null || target.length == 0) return 0;
    //     int m = target.length;
    //     int n = arr.length;
    //     int[][] dp = new int[m + 1][n + 1];
    //     for (int i = 1; i <= m; i++) {
    //         for (int j = 1; j <= n; j++) {
    //             if (target[i - 1] == arr[j - 1]) {
    //                 dp[i][j] = dp[i - 1][j - 1] + 1;
    //             } else {
    //                 dp[i][j] = Math.max(dp[i - 1][j], dp[i][ j - 1]);
    //             }
    //         }
    //     }
    //     return m - dp[m][n];
    // }
}
