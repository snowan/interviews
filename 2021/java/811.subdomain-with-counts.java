class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        if (cpdomains == null || cpdomains.length == 0) return new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String domain : cpdomains) {
            String[] words = domain.split(" ");
            int num = Integer.valueOf(words[0]);
            map.put(words[1], map.getOrDefault(words[1], 0) + num);
            for (int i = 0; i < words[1].length(); i++) {
                if (words[1].charAt(i) == '.') {
                    String w = words[1].substring(i + 1);
                    map.put(w, map.getOrDefault(w, 0) + num);
                } 
            }
        }
        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key) + " " + key);
        }
        return res;
    }
}
