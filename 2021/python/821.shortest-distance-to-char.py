"""
821. Shortest Distance to a Character

Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.

Example 1:

Input: s = "loveleetcode", c = "e"
Output: [3,2,1,0,1,0,0,1,2,2,1,0]
Example 2:

Input: s = "aaab", c = "b"
Output: [3,2,1,0]
 

Constraints:

1 <= s.length <= 104
s[i] and c are lowercase English letters.
c occurs at least once in s.
"""
def shortestToChar(s: str, c: str) -> List[int]:
        if not str or len(s) == 0:
            return None
        res, prev = [0] * len(s), -len(s)
        # left to right
        for i in range(len(s)):
            if s[i] == c:
                prev = i
            res[i] = i - prev
        # right to left
        prev = 2 * len(s)
        for i in range(len(s) - 1, -1, -1):
            if s[i] == c:
                prev = i
            res[i] = min(res[i], prev - i)
        return res
