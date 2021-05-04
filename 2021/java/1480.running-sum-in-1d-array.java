class Solution {
    // presum, sum[i] = nums[i] + sum[i-1]
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        for (int i = 1; i < len; i++) {
            res[i] = nums[i] + res[i - 1];
        }
        return res;
    }
}
