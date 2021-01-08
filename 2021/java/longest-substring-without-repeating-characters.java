class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
	int res = 1;
	int prev = 0;
        Map<Character, Integer> map = new HashMap<>();
	for (int i = 0; i < s.length(); i++) {
	    char curr = s.charAt(i);
	    if (map.containsKey(curr)) {
	        prev = Math.max(prev, map.get(curr) + 1);
	    }
	    map.put(curr, i);
	    res = Math.max(res, i - prev + 1);
	}
	return res;
    }
}

