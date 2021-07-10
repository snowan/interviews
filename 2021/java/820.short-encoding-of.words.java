class Solution {
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) return 0;
        // unique words in set
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String w : words) {
            // remove any word which is suffix 
            for (int j = 1; j < w.length(); j++) {
                set.remove(w.substring(j));
            }
        }
        int res = 0;
        for (String s : set) {
            res += s.length() + 1;
        }
        return res;
    }
}
