class Solution {
    // idea is: using min heap, 
    // 1. if next height > prev height (diff) > 0, use ladder
    // 2. if all ladders used, pop out the smallest diff, start using bricks
    // 3. if found bricks < 0, then cannot reach further, return
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int len = heights.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < len - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                pq.offer(diff);
            }
            if (pq.size() > ladders) {
                bricks -= pq.poll();
            }
            if (bricks < 0) return i;
        }
        return len - 1;
    }
}
