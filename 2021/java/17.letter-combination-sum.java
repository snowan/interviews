
class Solution {
    // solution 1: add each letter each level
    String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        res.add("");
        for (char ch : digits.toCharArray()) {
            res = combine(mapping[ch - '0'], res);
        }
        return res;
    }
    
    private List<String> combine(String digits, List<String> list) {
        List<String> res = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            for (String l : list) {
                res.add(l + c);
            }
        }
        return res;
    }
    
    
    // solution 2: backtracking
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        dfs(digits, 0, res, new StringBuilder());
        return res;
    }
    
    private void dfs(String digits, int index, List<String> res, StringBuilder path) {
        if (index > digits.length()) return;
        if (path.length() == digits.length()) {
            res.add(new String(path.toString()));
            return;
        }
        String currLetters = mapping[digits.charAt(index) - '0'];
        for (char ch : currLetters.toCharArray()) {
            path.append(ch);
            dfs(digits, index + 1, res, path);
            path.deleteCharAt(path.length() - 1);
        }  
    } 
}
