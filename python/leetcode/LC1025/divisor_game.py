class Solution:
    def divisorGame(self, N: int) -> bool:
        dp = [False for i in range(N + 1)]
        dp[0] = dp[1] = False
        for i in range(2, N + 1):
            for x in range(1, i):
                if i % x == 0 and (not dp[i - x]):
                    dp[i] = True
                    break
        return dp[N]
