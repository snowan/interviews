class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) return "";
        Collections.sort(d, (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String w : d) {
            int l = 0;
            int r = 0;
            int count = 0;
            while (l < w.length() && r < s.length()) {
                if (w.charAt(l) == s.charAt(r)) {
                    count++;
                    l++;
                }
                r++;
            }
            if (count == w.length()) return w;
        }
        return "";
    }
}
