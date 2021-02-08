class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";
        Set<String> skips = Set.of("..", ".", "");
        Stack<String> stack = new Stack<>();
        for (String curr : path.split("/")) {
            if (!stack.isEmpty() && "..".equals(curr)) {
                stack.pop();
            } else if (!skips.contains(curr)) {
                stack.push(curr);
            }
        }
        if (stack.isEmpty()) return "/";
        String res = "";
        while(!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res;
    }
}
