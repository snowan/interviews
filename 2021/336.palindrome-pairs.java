
class Solution {
    // #1. brute force, if words.length << word.length() 
    // time: O(n^2 * m)
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isPalindrome(words[i] + words[j])) {
                    res.add(Arrays.asList(i, j));
                }
                if (isPalindrome(words[j] +words[i])) {
                    res.add(Arrays.asList(j, i));
                }
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }


    // if word.length() << words.length, then split word
    // time (O(n*m^2) space: O(n)
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        // key: word, value: index
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            // split word into two parts [0, j], [j, len]. 
            // check if left is parlindrome, and use left as middle to form a new parlindrome, with [reverseRight]+[left]+[right] 
            // if reverseRight in words lists, then found one pair. 
            // do the same for right part
            for (int j = 0; j <= w.length(); j++) {
                String left = w.substring(0, j);
                String right = w.substring(j);
                // left as middle
                List<Integer> tmp = foundPair(left, map, right, i);
                if (tmp != null) set.add(Arrays.asList(tmp.get(1), tmp.get(0)));
                // right as middle
                tmp = foundPair(right, map, left, i);
                if (tmp != null) set.add(tmp);
            }
        }
        return new ArrayList<>(set);
    }
    
    private List<Integer> foundPair(String mid, Map<String, Integer> map, String other, int idx) {
        String reverseOther = new StringBuilder(other).reverse().toString();
        if (isPalindrome(mid) && map.containsKey(reverseOther) && map.get(reverseOther) != idx) {
            return Arrays.asList(idx, map.get(reverseOther));
        }
        return null;
    }
    
    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
