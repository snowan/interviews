class Solution {
    private static final Set<Character> left = ImmutableSet.of('(', '[', '{');
    private static final Map<Character, Character> map = ImmutableMap.of(
        ')', '(',
        ']', '[',
        '}', '{');
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        int len = s.length();
        if (len % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray) {
            if (left.contains(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || map.get(ch) != stack.pop()) return false;
            }
        }
        return stack.isEmpty();
    }
}
