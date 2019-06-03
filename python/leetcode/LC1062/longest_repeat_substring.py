class Solution:
    def longestRepeatingSubstring(self, S: str) -> int:
        n = len(S)
        substr = set()
        for leng in reversed(range(n + 1)):
            for l in range(n - leng + 1):
                r = l + leng - 1
                temp = S[l: r + 1]
                if (temp in substr):
                    return len(temp)
                substr.add(temp)
            substr.clear()
        return 0
