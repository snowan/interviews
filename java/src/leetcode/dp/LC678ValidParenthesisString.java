package leetcode.dp;

/**
 * 678. Valid Parenthesis String
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this
 * string is valid. We define the validity of a string by these rules:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:
 *
 * Input: "()"
 * Output: True
 * Example 2:
 *
 * Input: "(*)"
 * Output: True
 * Example 3:
 *
 * Input: "(*))"
 * Output: True
 * Note:
 *
 * The string size will be in the range [1, 100].
 */
public class LC678ValidParenthesisString {
  /**
   * Solution #1: recursive, when we check if it is valid parenthesis, use count,
   * 1. when encounter '(', count++,
   * 2. when encounter ')', count--
   * 3. for each char, we check whether count, if count < 0, return false
   * 4. return count == 0;
   * now add '*', we can add one more check, after step #2,
   * when encounter '*', we check 3 options, return true if any of the option is valid
   *  - '*' as '(', count + 1,
   *  - '*' as ')', count - 1
   *  - '*' as '*', count
   *
   * so we can recursively check count.
   *
   * TC: O(
   *
   */
  public boolean checkValidString(String s) {
    if (s == null || s.length() == 0) return true;
    return helper(s, 0, 0);
  }
  private boolean helper(String s, int idx, int count) {
    if (idx == s.length()) {
      if (count == 0) return true;
      return false;
    }
    if (count < 0) return false;
    char curr = s.charAt(idx);
    if (curr == '(') return helper(s, idx + 1, count + 1);
    else if (curr == ')') return helper(s, idx + 1, count - 1);
    else if (curr == '*') {
      return helper(s, idx + 1, count + 1) // '*' as '('
          || helper(s, idx + 1, count - 1) // '*' as ')'
          || helper(s, idx + 1, count); // '*' as string
    }
    return count == 0;
  }

  /**
   * solution #2. Greedy, from recursive solution, we know that when encounter '*', we need to check 3 options,
   * we can keep track off min count, and max count.
   */
  public boolean checkValidParenthesis(String s) {
    if (s == null || s.length() == 0) return true;
    int minCount = 0;
    int maxCount = 0;
    int len = s.length();
    for (int i = 0; i < len; i++) {
      char curr = s.charAt(i);
      if (curr == '(') {
        minCount++;
        maxCount++;
      } else if (curr == ')') {
        if (minCount > 0) minCount--;
        maxCount--;
      } else if (curr == '*') {
        if (minCount > 0) minCount--;
        maxCount--;
      }
      if (maxCount < 0) return false;
    }
    return minCount == 0;
  }
}
