class Solution {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length < 2) return true;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;
                // reset nums[i-1] = nums[i], if current is decreasing, so that some cases, we can cover. e.g [3,4,2,3]
                if (i < 2 || nums[i - 2] <= nums[i]) nums[i - 1] = nums[i];
                else nums[i] = nums[i - 1];
            }
            
            if (count >= 2) return false;
        }
        
        return true;
    }
}
