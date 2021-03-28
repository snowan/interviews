class Solution {
    // dp[i][j] -- substring(i,j) is palindrome
    // dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j).
    // when dp[i][j] true, count + 1
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = s.length();
        // len = 1 true
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // len = 2
        for (int i = 0; i + 1 < n; i++) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            res += dp[i][i + 1] ? 1 : 0;
        }
        // start from len = 3
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
                res += dp[i][j] ? 1 : 0;
                
            }
        }
        return res;
    }
}
