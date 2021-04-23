class Solution {
    // hashmap, key: preSum, value: count 
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) return 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        int max = 0;
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
                max = Math.max(max, preSum.get(sum));
            }
        }
        return wall.size() - max;
    }
}
