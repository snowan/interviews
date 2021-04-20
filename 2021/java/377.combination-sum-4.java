class Solution {
  
    // solution 1: recursive + hashmap (memo)
    Map<Integer, Integer> memo = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) return 0;
        if (target == 0) return 1;
        if (memo.containsKey(target)) return memo.get(target);
        int res = 0;
        for (int num : nums) {
            res += combinationSum4(nums, target - num);
        }
        memo.put(target, res);
        return res;
    }
  
  // dp
  public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
          for (int num : nums) {
            if (i >= num)
              dp[i] += dp[i - num];
          }
        }
        return dp[target];
    }
}
