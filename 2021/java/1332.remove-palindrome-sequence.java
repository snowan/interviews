class Solution {
    public int removePalindromeSub(String s) {
        if (s.isEmpty()) return 0;
        return s.equals(new StringBuilder(s).reverse().toString()) ? 1 : 2;
    }
}
