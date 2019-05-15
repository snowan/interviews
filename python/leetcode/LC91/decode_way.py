from typing import List

class Solution:
    def numDecodings(self, s: str) -> int:
        if not s:
            return 0
        return self.test(s, 0, [-1 for i in range(len(s))])

    def test(self, s: str, pos: int, res: List[int]) -> int:
        if pos >= len(s):
            return 1
        if res[pos] != -1:
            return res[pos]
        res_temp = 0
        if int(s[pos: pos + 1]) != 0:
            res_temp += self.test(s, pos + 1, res)
        if 10 <= int(s[pos: pos + 2]) <= 26:
            res_temp += self.test(s, pos + 2, res)
        res[pos] = res_temp
        return res_temp