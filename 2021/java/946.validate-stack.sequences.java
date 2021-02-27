class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        Stack<Integer> stack = new Stack<>();
        int len = pushed.length;
        int i = 0, j = 0;
        while (i < len && j < len) {
            if (pushed[i] != popped[j]) {
                stack.push(pushed[i++]);
            } else {
                i++;
                j++;
                while (!stack.isEmpty() && j < len && stack.peek() == popped[j]) {
                    stack.pop();
                    j++;
                }
            }
        }
        return stack.isEmpty();
    }
}
