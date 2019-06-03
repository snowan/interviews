class Solution:
    def longest_repeating_substring(self, s: str) -> int:
        if not s:
            return 0
        temp = set()
        for l in reversed(range(len(s))):
            for i in range(len(s) - l + 1):
                sub_str = s[i: i + l]
                if sub_str in temp:
                    return len(sub_str)
                else:
                    temp.add(sub_str)
        return 0