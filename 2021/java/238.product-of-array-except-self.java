class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int len = nums.length;
        int[] res = new int[len];
        int prod = nums[0];
        res[0] = 1;
        // from left to right
        for (int i = 1; i < len; i++) {
            res[i] = prod;
            prod *= nums[i];
        }
        // from right to leftt 
        prod = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            res[i] *= prod;
            prod *= nums[i];
        }
        return res;
    }
}
