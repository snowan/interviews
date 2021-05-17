
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) return res;
        s = s.substring(1, s.length() - 1);
        for (int i = 1; i < s.length(); i++) {
            List<String> left = generateValidStr(s.substring(0, i));
            List<String> right = generateValidStr(s.substring(i));
            for (String l : left) {
                for (String r : right) {
                    res.add("(" + l + ", " + r + ")");
                }
            }
        }
        
        return res;
    }
    private List<String> generateValidStr(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (s.charAt(0) == '0' && s.charAt(len - 1) == '0') { // "0xxxxx0 is invalid, only "0" valid
            if (len == 1) {
                res.add("0");
            }
            return res;
        }
        if (s.charAt(0) == '0') { // "0xxxxx" is valid only "0.xxxx"
            res.add("0." + s.substring(1));
            return res;
        }
        if (s.charAt(len - 1) == '0') { // "xxxx0", only valid is itself
            res.add(s);
            return res;
        }
        // other cases
        res.add(s);
        for (int i = 1; i < len; i++) { // xxxxx -> x.xxx, xx.xx, xxx.x valid
            res.add(s.substring(0, i) + "." + s.substring(i));
        }
            
        return res;
    }
}
