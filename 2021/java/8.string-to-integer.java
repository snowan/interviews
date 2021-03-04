class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        int idx = 0;
        int len = s.length();
        // discard leading white space
        while (idx < len && s.charAt(idx) == ' ') idx++;
        int sign = 1;
        // check sign "+" / "-"
        if (idx < len && (s.charAt(idx) == '+' || s.charAt(idx) == '-')) {
            sign = s.charAt(idx) == '+' ? 1 : -1;
            idx++;
        }
        long res = 0;
        while (idx < len && Character.isDigit(s.charAt(idx))) {
            res = res * 10 + (s.charAt(idx++) - '0');
            if (res > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        
        return (int) res * sign;
    }
}
