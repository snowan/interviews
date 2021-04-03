class Solution {
    // dp[i][j] - # of strings can form < i zeros and j ones.
    // at string s, either pick current s or do not pick s.
    // dp[i][j] = Math.max(dp[i][j], dp[i - zeroCount][j - oneCount] + 1) 
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] counts = countZeroOnes(s);
            for (int i = m; i >= counts[0]; i--) {
                for (int j = n; j >= counts[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - counts[0]][j - counts[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }
    private int[] countZeroOnes(String s) {
        int[] res = new int[2];
        for (char ch : s.toCharArray()) {
            res[ch - '0']++;
        }
        return res;
    }
}
