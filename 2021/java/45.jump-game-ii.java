class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= i + nums[i] && j < len; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[len - 1];
        
    }
}
