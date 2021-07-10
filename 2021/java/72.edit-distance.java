class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] cache = new int[m][n];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        return match(word1, word2, 0, 0, cache);
    }
    private int match(String word1, String word2, int i, int j, int[][] cache) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) return word1.length() - i;
        if (cache[i][j] != -1) return cache[i][j];
        if (word1.charAt(i) == word2.charAt(j)) {
            cache[i][j] = match(word1, word2, i + 1, j + 1, cache);
        } else {
            int insert = match(word1, word2, i, j + 1, cache);
            int delete = match(word1, word2, i + 1, j, cache);
            int replace = match(word1, word2, i + 1, j + 1, cache);
            cache[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }
        return cache[i][j];
    }
    
    // dp
     public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        // init
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // match
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int insert = dp[i + 1][j];
                    int delete = dp[i][j + 1];
                    int replace = dp[i][j];
                    dp[i + 1][j + 1] = Math.min(insert, Math.min(delete, replace)) + 1;
                }
            }
        }
        
        return dp[n][m];
    }
}
