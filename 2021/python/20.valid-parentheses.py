"""
 20. Valid Parentheses

 Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
Example 4:

Input: s = "([)]"
Output: false
Example 5:

Input: s = "{[]}"
Output: true


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.

"""

"""
Solution: using stack, left put into stack, when current is right,  check whether stack top left matches with current right, if stack empty or not match, return false. otherwise pop stack top, continue.

"""
def isValid(s: str) -> bool:
    if not s:
        return True
    size = len(s)
    if size % 2 != 0:
        return False
    maps = {')': '(', ']': '[', '}': '{'}
    sets = {'(', '[', '{'}
    stack = []
    for idx in range(size):
        curr = s[idx]
        if curr in sets:
            stack.append(curr)
        else:
            if not stack or maps[curr] != stack.pop():
                return False
    return len(stack) == 0

