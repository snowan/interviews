class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int len = nums.length;
        int l = len - 2;
        // find the first right idx which breaks descending order
        while (l >= 0 && nums[l] >= nums[l + 1]) l--;
        // find largest from right part and swap with left only when the array is not fully descending order, otherwise, reverse 
        if (l >= 0) {
            int r = len - 1;
            while (r >= 0 && nums[r] <= nums[l]) r--;
            swap(nums, l, r);
        }
        // reverse right part
        reverse(nums, l + 1, len - 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }
}
