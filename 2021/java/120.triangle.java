class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int rows = triangle.size();
        int[] dp = new int[rows];
        // init , last row 
        for (int c = 0; c < triangle.get(rows - 1).size(); c++) {
            dp[c] = triangle.get(rows - 1).get(c);
            
        }
        // start from last sencond row 
        for (int r = rows - 2; r >= 0; r--) {
            for (int c = 0; c <= r; c++) {
                dp[c] = Math.min(dp[c], dp[c + 1]) + triangle.get(r).get(c);
            }
        }
        return dp[0];
    }
}
