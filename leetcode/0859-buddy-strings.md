## Problem: 859. Buddy Strings

https://leetcode.com/problems/buddy-strings/description/

Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].

For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 

 Example 1:

 Input: s = "ab", goal = "ba"
 Output: true
 Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
 Example 2:

 Input: s = "ab", goal = "ab"
 Output: false
 Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
 Example 3:

 Input: s = "aa", goal = "aa"
 Output: true
 Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.
  

  Constraints:

  1 <= s.length, goal.length <= 2 * 104
  s and goal consist of lowercase letters.


## Solution 

1. if length of 2 strings are not equal, return False
2. if `s==goal`, then check whether there are any letter repeatable, yes, True. otherwise False
3. get pair of not equal letters, 
    a. if more than 2 different pairs, return False
    b. check pair[0] == reserverd pair[1]


**Time Complexity:** O(n)
**Space Complexity:** O(n)

```python
class Solution:
    def buddyStrings(self, s: str, goal: str) -> bool:
        if not s or len(s) < 2 or len(s) != len(goal):
            return False
        if s == goal:
            return len(set(s)) < len(s)

        diffs = [(a, b) for a, b in zip(s, goal) if a != b]
        return len(diffs) == 2 and diffs[0] == diffs[1][::-1]


```
