class Solution {
    // stack keep tracking invalid index,
    // remove all valid indicies. 
    // so between each index remain in stack, find longest 
    public int longestValidParentheses(String s) {
        if (s.isEmpty()) return 0;
        int len = s.length();
        // index 
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            if (curr == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else stack.push(i);
            }
        }
        if (stack.isEmpty()) return len;
        int end = len;
        int start = 0;
        int max = 0;
        while (!stack.isEmpty()) {
            start = stack.pop();
            max = Math.max(max, end - start - 1);
            end = start;
        }
    
        return Math.max(max, end);
    }
}
