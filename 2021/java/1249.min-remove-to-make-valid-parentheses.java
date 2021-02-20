class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<Integer> stack = new Stack<>();
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char curr = chs[i];
            if (curr == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    // invalid, to be removed
                    chs[i] = '#';
                }
            }
        }
        while (!stack.isEmpty()) {
            chs[stack.pop()] = '#';
        }
    
        return new String(chs).replaceAll("#", "");
    }
}
