## Problem: 2007. Find Original Array From Doubled Array

https://leetcode.com/problems/find-original-array-from-doubled-array/


An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

 

 Example 1:

 Input: changed = [1,3,4,2,6,8]
 Output: [1,3,4]
 Explanation: One possible original array could be [1,3,4]:
 - Twice the value of 1 is 1 * 2 = 2.
 - - Twice the value of 3 is 3 * 2 = 6.
 - - Twice the value of 4 is 4 * 2 = 8.
 - Other original arrays could be [4,3,1] or [3,1,4].

Example 2:
 -
 - Input: changed = [6,3,0,1]
 - Output: []
 - Explanation: changed is not a doubled array.

Example 3:
 -
 - Input: changed = [1]
 - Output: []
 - Explanation: changed is not a doubled array.
  

Constraints:
 -
 -  1 <= changed.length <= 105
 -  0 <= changed[i] <= 105

## Solution

The idea is that for smallest to biggest match. if small number has doubled number (bigger) match. 

1. sort the number, scan from smallest to biggest number
2. counter number frequencies, find match, reduce frequency, not found match, return []
3. handle 0 case, counter[0] should be even, otherwise no match


```python
class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        # handle basic case 
        if not changed or len(changed) % 2:
            return []
        # sort array 
        arr = sorted(changed)
        # count element frequencies
        count_freq = collections.Counter(changed)
        res = []
        # scan from smallest
        for num in arr:
            if count_freq[num] == 0:
                continue
            # check 2*num in count_freq 
            if not count_freq[2 * num]:
                return []
            res.append(num)
            # handle 0 case
            if num == 0 and count_freq[num] % 2:
                return []
            
            count_freq[num] -= 1
            count_freq[2 * num] -= 1


        return res

```
