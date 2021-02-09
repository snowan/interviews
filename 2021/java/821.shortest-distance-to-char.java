class Solution {
    public int[] shortestToChar(String s, char c) {
        if (s == null || s.length() == 0) return new int[0];
        int len = s.length();
        int[] res = new int[len];
        int prev = -len;
        // left to right
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) prev = i;
            res[i] = i - prev;
        }
        // right to left
        prev = 2 * len;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == c) prev = i;
            res[i] = Math.min(res[i], prev - i);
        }
        return res;
    }
}
