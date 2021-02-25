"""
856. Score of Parentheses

Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6
 

Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50

"""
def scoreOfParentheses(self, S: str) -> int:
    if not S:
        return 0
    res, lvl = 0, 0
    for i in range(len(S)):
        lvl += 1 if S[i] == '(' else -1
        if S[i] == ')' and S[i - 1] == '(':
            res += 1 << lvl
    return res
