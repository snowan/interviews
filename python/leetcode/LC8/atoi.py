class Solution:
    def myAtoi(self, str: str) -> int:
        max_int = 2**31 - 1
        min_int = -2**31
        if not str:
            return 0
        str_strip = str.strip()
        if not str_strip or (not str_strip[0].isdigit() and str_strip[0] != '+' and str_strip[0] != '-'):
            return 0
        pos = True
        str_it = iter(str_strip)
        if not str_strip[0].isdigit():
            pos = str_strip[0] == '+'
            next(str_it)
        res = 0.0
        for c in str_it:
            if not c.isdigit():
                break
            res = res * 10 + int(c)
            if not pos and res * -1 < min_int:
                return min_int
            elif pos and res > max_int:
                return max_int
        return int(res) if pos else -int(res)