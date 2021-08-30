class Solution {
    // solution: check charater index with every pair (from 0 - (n-1))
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length < 2) return true;
        Map<Character, Integer> index = new HashMap();
        for (int i = 0; i < order.length(); i++) {
            index.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!isValid(index, words[i], words[i + 1])) return false;
        }
        return true;
    }
    private boolean isValid(Map<Character, Integer> map, String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int aIdx = 0;
        int bIdx = 0;
        while (aIdx < aLen && bIdx < bLen) {
            int aCurr = map.get(a.charAt(aIdx));
            int bCurr = map.get(b.charAt(bIdx));
            if (aCurr > bCurr) return false;
            else if (aCurr == bCurr) {
                if (aIdx < aLen - 1 && bIdx == bLen - 1) return false;
            } else if (aCurr < bCurr) return true;
            aIdx++;
            bIdx++;
        }
        return true;
    }
}
