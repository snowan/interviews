class Solution {
    // BFS
    // time: O(N) -- N is the number of keys
    // space: O(N)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) return false;
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> nextKeys = rooms.get(curr);
            if (nextKeys.size() == 0) continue;
            for (int key : nextKeys) {
                if (visited.add(key))
                    queue.offer(key);
            }
        }
        
        return visited.size() == rooms.size();

    }
}
