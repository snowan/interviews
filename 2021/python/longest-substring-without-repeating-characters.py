"""
Given a string s, find the length of the longest substring without repeating characters.

 

 Example 1:

 Input: s = "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: s = "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: s = "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 Example 4:

 Input: s = ""
 Output: 0
  

  Constraints:

  0 <= s.length <= 5 * 104
  s consists of English letters, digits, symbols and spaces.
"""

def lengthOfLongestSubstring(s:str) -> int:
    # keep track of last seen char index
    idx_map = {}
    res, prev_idx = 0, 0
    for i, ch in enumerate(s):
        if ch in idx_map:
            # if seen char in map, update prev index
            prev_idx = max(prev_idx, idx_map[ch] + 1)
        res = max(res, i - prev_idx + 1)
        idx_map[ch] = i
    return res

def main():
    test_data = ["", "bbbb", "abcabcbb", "pwwkew"]
    expected_res = [0, 1, 3, 3]
    for i in range(len(test_data)):
        res = lengthOfLongestSubstring(test_data[i])
        print(expected_res[i] == res) 




if __name__ == "__main__":
    main()
