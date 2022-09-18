## Problem: 42. Trapping Rain Water

https://leetcode.com/problems/trapping-rain-water/

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 
Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

 Constraints:

 n == height.length
 1 <= n <= 2 * 104
 0 <= height[i] <= 10^5


## Solution

The idea is to think of how water can be trapped, only when middle < left and middle < right, water can be trapped in the middle. so we can define max_left and max_right, 

then use left and right, two pointers, 

if max_left < max_right, we move left to the right, 
- if max_left < left, assign left to max_left, 
- if max_left > left, then water trapped, add height[max_left] - height[left] to res

if max_left >= max_right, we move right to the left
- if max_right < right, assign right to max_left 
- if max_right > right, then water trapped at right, add height[max_right] - height[right] to res

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        if not height:
            return 0
        max_left, max_right = 0, len(height) - 1
        left, right, res = 1, len(height) - 2, 0
            while left <= right:
                if height[max_left] < height[max_right]:
                    if height[max_left] < height[left]:
                        max_left = left
                    else:
                        res += height[max_left] - height[left]
                    left += 1
                else:
                    if height[max_right] < height[right]:
                        max_right = right
                    else:
                        res += height[max_right] - height[right]
                    right -= 1

        return res

```

