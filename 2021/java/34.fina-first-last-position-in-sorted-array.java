class Solution {
    // sort array -> binary search
    // 1. find most left position for given target. 
    // 2. check most left position is in array, if out of boundary, return {-1,-1}
    // 3. find most left position (left) for given (target + 1), so that most right position for given target will be left - 1.
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = binarySearch(nums, target);
        if (first == nums.length || nums[first] != target)  {
            return new int[]{-1, -1};
        }
        int last = binarySearch(nums, target + 1) - 1;
        return new int[]{first, last};
    }
    private int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
