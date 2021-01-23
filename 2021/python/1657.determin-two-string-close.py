"""
1657. Determine if Two Strings Are Close

Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.



Example 1:

Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
Example 2:

Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
Example 3:

Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"
Example 4:

Input: word1 = "cabbba", word2 = "aabbss"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.


Constraints:

1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.
"""

def closeStrings(word1: str, word2: str) -> bool:
    # if not the same length, not the same pattern
    if len(word1) != len(word2):
        return False
    # count character frequency
    count1 = {ch : word1.count(ch) for ch in set(word1)}
    count2 = {}
    # count word2 frequency and check whether character in word1, if not, terminate early
    for key in word2:
        if key not in count1:
            return False
        count2[key] = count2.get(key, 0) + 1
    # sort dict by values (frequency)
    count1 = sorted(count1.values())
    count2 = sorted(count2.values())
    # check values are equal, not equal, terminate early
    for v1, v2 in zip(count1, count2:
        if v1 != v2:
            return False
    return True
