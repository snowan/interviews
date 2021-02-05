class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;

        for (int num : nums) {
            if (!map.containsKey(num + 1)) continue;
            res = Math.max(res, map.get(num) + map.getOrDefault(num + 1, 0));
        }
        return res;
    }
}
