class Solution {
    public int maxAbsoluteSum(int[] nums) {
        // kadane's algorithm, get maxSubSum, minSubSum, then max max(maxSubSum, minSubSum)
        if (nums == null || nums.length == 0) return 0;
        int maxSubSum = nums[0];
        int maxCurrSum = 0;
        int minSubSum = nums[0];
        int minCurrSum = 0;
        for (int num : nums) {
            maxCurrSum = Math.max(num, maxCurrSum + num);
            maxSubSum = Math.max(maxSubSum, maxCurrSum);
            
            minCurrSum = Math.min(num, minCurrSum + num);
            minSubSum = Math.min(minSubSum, minCurrSum);
        }
        return Math.max(Math.abs(maxSubSum), Math.abs(minSubSum));
    }
}
