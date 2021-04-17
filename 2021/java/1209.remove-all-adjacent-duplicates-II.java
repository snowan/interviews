class Solution {
    public String removeDuplicates(String s, int k) {
        if (s.isEmpty() || s.length() < k) return s;
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!stack.isEmpty() && stack.peek().ch == ch) {
                stack.peek().count++;
            } else {
                stack.push(new Node(ch));
            }
            if (stack.peek().count == k) stack.pop();
        }
        
        String res = "";
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            String temp = "";
            while (curr.count-- > 0) {
                temp += curr.ch;
            }
            res = temp + res;
        }
        return res;
    }
}
class Node {
    char ch;
    int count;
    public Node(char ch) {
        this.ch = ch;
        this.count = 1;
    }
}
