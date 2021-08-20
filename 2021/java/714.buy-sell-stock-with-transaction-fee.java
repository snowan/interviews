class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] buy = new int[n]; // buy[i] -- ith day hold maximum profit on buy status
        int[] sell = new int[n]; // sell[i] -- ith day hold maximum profit on sell status
        buy[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]); // not buy on ith day, or buy ith day from sell prev day
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee); // not sell on ith day, or sell from prev day 
        }
        return sell[n - 1];
    }
}
