class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) return false;
        int len = graph.length;
        // colors to split nodes into 2 category, blue: 1; red: -1, not visited node: 0
        int[] colors = new int[len];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            // already visited, skip
            if (colors[i] != 0) continue;
            queue.offer(i);
            colors[i] = 1;
     
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    // node not visited, color it with a different color than curr
                    if (colors[next] == 0) {          
                        colors[next] = -colors[cur]; 
                        queue.offer(next);
                    } else if (colors[next] != -colors[cur]) {   // If it is colored and its color is different, return false;
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
