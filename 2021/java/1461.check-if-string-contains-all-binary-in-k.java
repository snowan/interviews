class Solution {
    // all possible binary for k has 2^k , find all possible size == k substring in given string, and put into set (dedup), check set size == 1<<k.
    public boolean hasAllCodes(String s, int k) {
        if (s == null || s.length() < k) return false;
        Set<String> seen = new HashSet<>();
        // find all possible k size substring in s, and check size == 1<<k
        for (int i = k; i <= s.length() && seen.size() < 1 << k; i++) {
            seen.add(s.substring(i - k, i));
        }
        return seen.size() == 1 << k;
    }
}
