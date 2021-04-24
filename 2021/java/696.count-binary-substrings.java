
class Solution {
    // awesome solution: https://leetcode.com/problems/count-binary-substrings/discuss/108625/JavaC%2B%2BPython-Easy-and-Concise-with-Explanation
    public int countBinarySubstrings(String s) {
        if (s.isEmpty() || s.length() == 1) return 0;
        int len = s.length();
        int res = 0;
        int curr = 1;
        int prev = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) curr++;
            else {
                res += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            }
        }
        res += Math.min(prev, curr);
        return res;
    }
}
