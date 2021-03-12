class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return amount == 0 ? 0 : -1;
        int n = coins.length;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i >= c && dp[i - c] != -1) {
                    min = Math.min(min, dp[i - c] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }
}
