## Problem: 1996 The number of week characters in the game 

https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/

You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.

A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.

Return the number of weak characters.

 

 Example 1:

 Input: properties = [[5,5],[6,3],[3,6]]
 Output: 0
 Explanation: No character has strictly greater attack and defense than the other.
 Example 2:

 Input: properties = [[2,2],[3,3]]
 Output: 1
 Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 Example 3:

 Input: properties = [[1,5],[10,4],[4,3]]
 Output: 1
 Explanation: The third character is weak because the second character has a strictly greater attack and defense.
  

  Constraints:

  2 <= properties.length <= 105
  properties[i].length == 2
  1 <= attacki, defensei <= 105


## Solution

### Solution 1: 

naive solution is to compare every 2 pairs, and find out both attach and defend greater, count the result. 

TC: O(n^2)


### Solution 2: 

from solution 1, think of a way to only compare 1 dimension, for example, if attach already sorted, and found when defense is greater, how to make sure defense greater will always be true? if first sort attach in ascending order and defense sorted in reserse order, then it satisfy our goal. 

1. sort (attack, defense) pair with attack ascending order, and defence in descending order. 
2. ignore attack, only compare defense, use stack,
3. if defense > stack[-1], pop out top, and continue,
4. count result


```python
class Solution:
    def numberOfWeakCharacters(self, properties: List[List[int]]) -> int:
        if not properties:
            return 0
        # sort 
        properties.sort(key=lambda x: (x[0], x[1]))

        # stack 
        stack, res = [], 0
        # only compare defense
        for _, d in properties:
            while stack and stack[-1] < d:
                res += 1
                stack.pop()
            stack.append(d)

        return res

```
