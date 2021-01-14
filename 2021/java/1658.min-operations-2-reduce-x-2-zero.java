/*
 * 1658. Minimum Operations to Reduce X to Zero
 *
 */

class Solution {
    public int minOperations(int[] nums, int x) {
        int target = Arrays.stream(nums).sum() - x;
        if (target == 0) return nums.length;
        int sum = 0;
        int len = nums.length;
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target)) {
                res = Math.max(res, i - map.get(sum - target));
            }
            map.put(sum, i);
            
        }
        
        return res == Integer.MIN_VALUE ? -1 : len - res;
    }
}
