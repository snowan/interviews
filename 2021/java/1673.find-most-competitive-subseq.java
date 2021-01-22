class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        if (nums == null || nums.length <= k) return nums;
        Stack<Integer> stack = new Stack<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[i] < stack.peek() && len - i + stack.size() > k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
