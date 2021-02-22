class Solution {
    // BFS
    public List<String> letterCasePermutation(String S) {
        if (S == null || S.length() == 0) return new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            // skip digits
            if (Character.isDigit(ch)) continue;
            int size = queue.size();
            while (size-- > 0) {
                char[] chs = queue.poll().toCharArray();
                // update uppercase at pos i
                chs[i] = Character.toUpperCase(ch);
                queue.offer(new String(chs));
                
                // update lowercase at pos i
                chs[i] = Character.toLowerCase(ch);
                queue.offer(new String(chs));
            }
        }
        
        return new ArrayList(queue);
    }
    
    // DFS
    public List<String> letterCasePermutation(String S) {
        if (S == null || S.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        helper(S.toCharArray(), res, 0);
        return res;
    }
    private void helper(char[] chs, List<String> res, int pos) {
        if (pos == chs.length) {
            res.add(new String(chs));
            return;
        }
        // digits case
        if (Character.isDigit(chs[pos])) {
            helper(chs, res, pos + 1);
            return;
        }
        // upper case 
        chs[pos] = Character.toUpperCase(chs[pos]);
        helper(chs, res, pos + 1);
        // lower case
        chs[pos] = Character.toLowerCase(chs[pos]);
        helper(chs, res, pos + 1);
    }
}
