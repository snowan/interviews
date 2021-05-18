class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        if not words or len(words) == 0:
            return 0
        dp = {}
        res = 1
        for w in sorted(words, key=len):
            dp[w] = 1
            
            # for each word, check prev (remove 1 letter), check whether it is in dp
            # if already in dp, then currently, res = dp[prev] + 1, otherwise dp[w]
            for i in range(len(w)):
                prev = w[:i] + w[i + 1:]
                if prev in dp:
                    dp[w] = max(dp[prev] + 1, dp[w])
                    res = max(res, dp[w])
        
        return res
