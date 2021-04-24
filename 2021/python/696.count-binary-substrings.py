class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        if not s or len(s) == 0:
            return 0
        prev, curr, res = 0, 1, 0
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                curr += 1
            else:
                res += min(prev, curr)
                prev = curr
                curr = 1
        res += min(prev, curr)
        return res
