## Problems: 336. Palindrome Pairs

https://leetcode.com/problems/palindrome-pairs/

Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.

 

 Example 1:

 Input: words = ["abcd","dcba","lls","s","sssll"]
 Output: [[0,1],[1,0],[3,2],[2,4]]
 Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

 Example 2:

 Input: words = ["bat","tab","cat"]
 Output: [[0,1],[1,0]]
 Explanation: The palindromes are ["battab","tabbat"]

 Example 3:

 Input: words = ["a",""]
 Output: [[0,1],[1,0]]
  

  Constraints:

  1 <= words.length <= 5000
  0 <= words[i].length <= 300
  words[i] consists of lower-case English letters.


## Solutions

### Solution 1:

the idea is to reconstruct the word from prefix and suffix, and look up reverted prefix or suffix in map, word = [reverted suffix]+[prefix]+[suffix], if prefix is palinedrome, then check reverted suffix is in map, if yes, then word is palindrome. same idea for suffix is palinedrome and check reverted prefix.  

1. build a map with {word: index} from words
2. for each word, split into prefix `w[:i]` and suffix `w[i:]`, 
    a. if prefix is palindrome, then check whether reverted(suffix) = suffix[::-1] is in word map, if exists, then found a pair, if not, continue
    b. if suffix is palindrome, then check whether reverted(prefix) = prefix[::-1] is in word map, if exists, then found a pair, if not, continue

**TC**: O(n * w^2) -- n is the length of words, w is the average length of the word

```python
class Solution:
    def palindromePairs(self, words: List[str]) -> List[List[int]]:
        if not words:
            return []

        def is_palindrome(s):
            return s == s[::-1]

        # build a word map
        word_dict = {w: idx for w, idx in enumerate(words)}

        res = []
        for idx, w in enumerate(words):
            n = len(w)
            for i in range(n+1):
                prefix, suffix = w[:i], w[i:]
                
                if is_palindrome(prefix):
                    reverted = suffix[::-1]
                    if reverted != w and reverted in word_dict:
                        res.append([word_dict[reverted], idx])
                if i != n and is_palindrome(suffix):
                    reverted = prefix[::-1]
                    if reverted != w and reverted in word_dict:
                        res.append([idx, word_dict[reverted]])
        return res

```



### Solution 2 
