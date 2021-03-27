// solution: use 2 maps to count letter frequencies.
// when count each word in B, update map with maximum counts.
// check each word in A, if letter frequency < letter appears in B frequency, or not exist. return false
class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        Map<String, Map<Character,Integer>> map = new HashMap<>();
        for (String a : A) {
            Map<Character, Integer> freq = getCharFreq(a);
            map.put(a, freq);
        }
        Map<Character, Integer> countB = new HashMap<>();
        for (String b : B) {
            Map<Character, Integer> curr = getCharFreq(b);
            for (char key : curr.keySet()) {
                countB.put(key, Math.max(countB.getOrDefault(key, 0), curr.get(key)));
            }
        }
        List<String> res = new ArrayList<>();
        for (String a : A) {
            if (isSubset(map.get(a), countB)) {
                res.add(a);
            }
        }
        return res;
    }
    private boolean isSubset(Map<Character, Integer> map, Map<Character, Integer> b) {
        for (char key : b.keySet()) {
            if (!map.containsKey(key) || map.get(key) < b.get(key)) return false;
        }
        return true;
    }
    private Map<Character, Integer> getCharFreq(String a) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : a.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }
}
