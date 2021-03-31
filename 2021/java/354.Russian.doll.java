class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 2) return envelopes.length;
        // sort by start 
        // e.g [[30,50],[12,2],[3,4],[12,15]] 
        // after sort -> [[3,4],[12,15,[12,2],[30,50]]], start already sorted, so the question become
        // find longest subsequence of [4,15,2,50]
        // use binary search index, smallest index > current num
        // Consider the example:
        // input: [0, 8, 4, 12, 2]
        // dp: [0]
        // dp: [0, 8]
        // dp: [0, 4]
        // dp: [0, 4, 12]
        // dp: [0 , 2, 12]
        // O(nlogn)
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int len = envelopes.length;
        int[] dp = new int[len];
        int res = 0;
        for (int[] env : envelopes) {
            int idx = findPos(dp, res, env[1]);
            dp[idx] = env[1];
            if (idx == res) res++;
        }
        
        return res;
    }
    private int findPos(int[] dp, int size, int target) {
        int l = 0;
        int r = size;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (dp[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
