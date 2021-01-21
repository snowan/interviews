"""
5. Longest Palindromic Substring

Given a string s, return the longest palindromic substring in s.

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters (lower-case and/or upper-case),
"""

"""
Thought process:
1. if single charater (e.g "a") -- palindrome
2. if 2 same characters (e.g "aa") -- palindrome
3. if #1 between #2 (e.g "aba") -- palindrome
4. if outer characters (left most and right most equal) and between is valid palindrome (e.g "abcba") -- palindrome

so we can maintain a dp, set to true (1) when palindrome, and false (0) when not palindrome,
for every left most and right most, check s[left] == s[right] and verify dp[left + 1][right - 1] true

e.g s = "abcba"
when left = 0 ("a"), right = 4 ("a"), s[left] == s[right], and check "bcd" palindrome, so dp[left + 1][right - 1]
then mark true, and track longest and start position.

result substring:  s[start, start + longest]
"""
def longestPalindrome(s: str) -> str:
    if not s or len(s) < 2:
        return s
    size = len(s)
    dp = [[0 for j in range(size)] for i in range(size)]
    # init dp (single character set to true)
    for i in range(size):
        dp[i][i] = 1
    start, longest = 0, 1
    # reverse traverse
    for left in range(size, -1, -1):
        for right in range(left + 1, size):
            if s[left] == s[right]:
                if right - left == 1 or dp[left + 1][right - 1]:
                    dp[left][right] = 1
                    if longest < right - left + 1:
                        start, longest = left, right - left + 1
    return s[start : start + longest]


