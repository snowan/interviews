class Solution {
    // solution 1: recursion
    // base case: () -- return 1
    // case 1: (A)(B) -> (A) + (B) 
    // case 2: ((A)) -> 2 * (A) 
    public int scoreOfParentheses(String S) {
        return helper(S, 0, S.length() - 1);
    }
    private int helper(String s, int l, int r) {
        if (r - l == 1) return 1; // () base case
        int count = 0;
        for (int i = l; i < r; i++) {
            if (s.charAt(i) == '(') count++;
            else if (s.charAt(i) == ')') count--;
            if (count == 0) { // balanced, (A)(B) -> (A) + (B)
                return helper(s, l, i) + helper(s, i + 1, r);
            }
        }
        // case (((A))) -> 2 * ((A))
        return 2 * helper(s, l + 1, r - 1); 
    }
    
    // solution 2: layer by layer travese
    // (()(())) -> (()) + ((())) = 2^1 + 2^2 = 6
    // () -> 2^0 = 1
    // (()()) -> (()) + (()) -> 2^1 + 2^1 = 4
    public int scoreOfParentheses(String S) {
        int res = 0;
        int lvl = 0;
        int len = S.length();
        for (int i = 0; i < len; i++) {
            char ch = S.charAt(i);
            if (ch == '(') lvl++;
            else lvl--;
            if (ch == ')' && S.charAt(i - 1) == '(') {
                res += 1 << lvl;
            }
            
        }
        return res;
    }
    
}
