class Solution {
    public boolean isCloseStrings(String word1, String word2) {
        if (word1 == null) return word2 == null;
        if (word2 == null) return word2 == null;
        if (word1.length() != word2.length()) return false;
        // word ony contains lower case 26 letters
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (char ch : word1.toCharArray) {
            count1[ch - 'a']++;
        }
        for (char ch : word2.toCharArray) {
            // if not in word1, not the same pattern, terminate early
            if (count1[ch - 'a'] < 1) return false;
            count2[ch - 'a']++;
        }
        Arrays.sort(count1);
        Arrays.sort(count2);
        for (int i = 0; i < 26; i++) {
            // not the same pattern, terminate ealry
            if (count1[i] != count2[i]) return false;
        }
        return true;
    }
}
