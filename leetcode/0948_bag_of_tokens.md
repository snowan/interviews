## Problem: 948. Bag of Tokens

https://leetcode.com/problems/bag-of-tokens/

You have an initial power of power, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).

Your goal is to maximize your total score by potentially playing each token in one of two ways:

If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
Each token may be played at most once and in any order. You do not have to play all the tokens.

Return the largest possible score you can achieve after playing any number of tokens.

 

 Example 1:

 Input: tokens = [100], power = 50
 Output: 0
 Explanation: Playing the only token in the bag is impossible because you either have too little power or too little score.
            
 Example 2:

 Input: tokens = [100,200], power = 150
 Output: 1
 Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
 There is no need to play the 1st token since you cannot play it face up to add to your score.
 
 Example 3:

 Input: tokens = [100,200,300,400], power = 200
 Output: 2
 Explanation: Play the tokens in this order to get a score of 2:
 1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
 2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
 3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
 4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.
  

  Constraints:

  0 <= tokens.length <= 1000
  0 <= tokens[i], power < 104


## Solution

in order to get maximum score, use least power to buy tokens, score +1, and get most tokens when power < tokens[i], and score-1, so we can sort tokens, spend min power to gain score, and gain most tokens when lose score

1. sort tokens from ascending order
2. put sorted token into queue,
3. if queue is not empty and current power > left of the queue (min) 
4. if current power < left of the queue, then need to gain tokens, and lost 1 score
5. continue until one of the condition is not meet, conditions
    a. power > min(tokens)
    b. when power < min(tokens), score > 0


```python
class Solution:
    def bagOfTokensScore(self, tokens: List[int], power: int) -> int:
        if not tokens:
            return 0
        # sort token
        tokens.sort()
        # put token into queue 
        queue = collections.deque(tokens)
        score, res = 0, 0
        while queue and (power >= queue[0] or score):
            while queue and power < queue[0]:
                score += 1
                power -= queue.popleft()
            res = max(res, score)
            if power > queue[0] and score:
                score -= 1
                power += queue.pop()
        return res

```
