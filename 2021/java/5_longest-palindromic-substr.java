/**
 * 5. Longest Palindromic Substring
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
*/
class Solution {
    public String longestPalindromicSubstr(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0;
        int longest = 1;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int l = len - 1; l < len; l--) {
            for (int r = l + 1; r < len; r++) {
                if (s.charAt(l) == s.charAt(r)) {
                    if (r - l == 1 || dp[l + 1][r - 1]) {
                        if (longest < r - l + 1) {
                            longest = r - l + 1;
                            start = l;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + longest + 1);
    }
}
