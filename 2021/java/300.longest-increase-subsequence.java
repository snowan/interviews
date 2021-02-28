class Solution {
    // solution #1: dp 
    // dp[i] - longest increase subsequence for nums with range [0:i], ending with nums[i]
    // for j in range[0:i], nums[j] < nums[i] -> dp[i] = max(dp[i], dp[j] + 1) 
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2) return nums.length;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        
        return res;
    }
    
    // solution #2: greedy, binary search
    // e.g  [10,9,2,5,3,7,101,18]
    // piles[0,0,0,0,0,0,0,0], size = 0;
    // num = 10, idx = 0 -> piles[10,0,0,0,0,0,0,0], size = 1
    // num = 9,  idx = 0 -> piles[9,0,0,0,0,0,0,0],  size = 1
    // num = 2,  idx = 0 -> piles[2,0,0,0,0,0,0,0],  size = 1
    // num = 5,  idx = 1 -> piles[2,5,0,0,0,0,0,0],  size = 2
    // num = 3,  idx = 1 -> piles[2,3,0,0,0,0,0,0],  size = 2
    // num = 7,  idx = 2 -> piles[2,3,7,0,0,0,0,0],  size = 3
    // num = 101,idx = 3 -> piles[2,3,7,101,0,0,0,0],  size = 4
    // num = 18, idx = 3 -> piles[2,3,7,18,0,0,0,0],  size = 4
    // longest size = 4
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] piles = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            // find the first index > num
            int idx = binarySearch(piles, size, num);
            // replace with smaller value to keep current piles size not change 
            piles[idx] = num;
            if (idx == size) size++;
        }
        
        return size;
    }
    private int binarySearch(int[] piles, int size, int target) {
        int l = 0;
        int r = size;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (piles[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
    
}
