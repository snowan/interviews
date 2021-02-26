class Solution {
    // sort array, keep track of left and right, if not align, then return
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2) return 0;
        int len = nums.length;
        if (nums[len - 1] < nums[0]) return len;
        int[] tmp = Arrays.copyOf(nums, len);
        Arrays.sort(tmp);
        int l = 0;
        int r = len - 1;
        while (l < r) {
            if (tmp[l] == nums[l]) l++;
            if (tmp[r] == nums[r]) r--;
            if (tmp[l] != nums[l] && tmp[r] != nums[r]) return r - l + 1;
        }
        return r == l ? 0 : r - l + 1;
    }
  
  // keep tracking min, max from left and right, 
  public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        if (len < 2) return 0;
        int min = nums[len - 1];
        int max = nums[0];
        int l = -1;
        int r = -2;
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                r = i;
            }
            min = Math.min(min, nums[len - 1 - i]);
            if (nums[len - 1 - i] > min) {
                l = len - 1 - i;
            }
        }
        return r - l + 1;
    }
  
}
