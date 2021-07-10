class Solution {
    public int minimumDeletions(String s) {
        if (s.length() < 2) return 0;
        int n = s.length();
        // preB[i] -- number of bs contains from left to right
        int[] preB = new int[n];
        preB[0] = s.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            preB[i] = preB[i - 1] + (s.charAt(i) == 'b' ? 1 : 0);
        }
        
        // sufA[i] -- number of as contains from right to left
        int[] sufA = new int[n];
        sufA[n - 1] = s.charAt(n - 1) == 'a' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            sufA[i] = sufA[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }
        
        // min (string contains all 'a'(remove all 'b') or string contains all 'b' (remove all 'a'))
        int res = Math.min(preB[n - 1], sufA[0]);
        for (int i = 0; i < n - 1; i++) {
            // remove 'b' from left + remove 'a' from right
            // X | XXXXX
            // i   i + 1
            res = Math.min(res, preB[i] + sufA[i + 1]);
        }
        
        return res;
    }
    
    public int minimumDeletions(String s) {
        int res = 0, countB = 0;
        for (int i = 0; i < s.length(); i++) {
            // keep a or remove a to make b
            if (s.charAt(i) == 'a') res = Math.min(++res, countB);
            else countB++;
        }
        return res;
    }
}
