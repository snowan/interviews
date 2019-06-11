from typing import List
import collections

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        if not s or not words:
            return []
        w_num = len(words)
        w_len = len(words[0])
        w_count = collections.defaultdict(int)
        for word in words:
            w_count[word] += 1
        res = []
        for i in range(w_len):
            temp_count = collections.defaultdict(int)
            left = i
            for right in range(i, len(s) - w_len + 1, w_len):
                temp_word = s[right: right + w_len]
                if temp_word in w_count:
                    temp_count[temp_word] += 1
                    while temp_count[temp_word] > w_count[temp_word]:
                        temp_count[s[left: left + w_len]] -= 1
                        left += w_len
                    if right + w_len - left == w_num * w_len:
                        res.append(left)
                        temp_count[s[left: left + w_len]] -= 1
                        left += w_len
                else:
                    left = right + w_len
                    temp_count.clear()
        return res