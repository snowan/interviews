package leetcode.dpgreedy;

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
   * so recursively check count.
   * TC: O(3 ^ n) - n is the number of '*'
   * SC: O(3 ^ n)
   */
  public boolean checkValidString(String s) {
    if (s == null || s.length() == 0) return true;
    return helper(s, 0, 0);
  }
  private boolean helper(String s, int idx, int count) {
    if (idx == s.length()) {
      return count == 0;
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
   * we can keep track min count, and max count.
   * 1. when encounter '(', minCount++, maxCount++
   * 2. when encounter ')', maxCount--; and check if (minCount > 0), minCount--
   * 3. when encounter '*', maxCount++; and check if (minCount > 0), minCount--.
   * 4. check if maxCount < 0, then no way it can be valid, return false;
   * 5. check minCount == 0;
   *
   * for example: "(**))"
   * idx = 0, count = 1, minCount = 1, maxCount = 1
   * idx = 1, count can be, [0, 1, 2] (0 - '*' as ')', 1 - '*' as '*', 2 - '*' as '(') - minCount = 0, maxCount = 2
   * idx = 2, count have 9 possible value,
   *  when count=0, count cane be [-1, 0, 1]
   *  when count=1, count can be [0, 1, 2]
   *  when count=2, count can be [1, 2, 3]
   *  summarize, count can be [0, 1, 2, 3] - minCount = 0, maxCount = 3
   * idx = 3, count = [0, 1, 2] - minCount = 0, maxCount = 2
   * idx = 4, count = [0, 1] - minCount = 0, maxCount = 1.
   * another check we can realize, if maxCount < 0, there is no way we can make string valid.
   *
   * from above observation, we can get above steps (1~5) on how to check minCount, and maxCount.
   *
   * TC: O(n) - n is String length
   * SC: O(1)
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
        maxCount++;
      }
      if (maxCount < 0) return false;
    }
    return minCount == 0;
  }
}
