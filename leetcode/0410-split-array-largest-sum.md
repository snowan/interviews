## Problem: 410. Split Array Largest Sum

https://leetcode.com/problems/split-array-largest-sum/

Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

 

 Example 1:

 Input: nums = [7,2,5,10,8], m = 2
 Output: 18
 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.

 Example 2:

 Input: nums = [1,2,3,4,5], m = 2
 Output: 9

 Example 3:

 Input: nums = [1,4,4], m = 3
 Output: 4
  

  Constraints:

  1 <= nums.length <= 1000
  0 <= nums[i] <= 106
  1 <= m <= min(50, nums.length)


## Solution

The idea of this problem is that it is given search range and target value, natually, use Binary search 

what is the search range? 

since continuous subarray is non-empty, at least 1 element, in order to find min of largest sum among m subarrays, the left(min_val) = max(nums), right(max_val) = sum(nums).

now we have search range (max(nums), sum(nums))

for each sum in a search range, now need to find out whether sum (x) in [left, right] can split array into m subarrays, we calculate number of subarrays with sub_sum < current sum, 
- if number of splits <= m, then continue left, until find the min
- if number of splits > m, then continue to right half 

**Time Complexity:** O(n) * O(log(s)) -> O(nlogs) --- n is the number of nums, s is the search range 
**Space Complexity:** O(1)

```python
class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        if not nums:
            return 0
        left, right = max(nums), sum(nums)
        res = 0
        while left <= right:
            candidate_sum = (left + right) // 2
            if self.min_splits_sum(nums, candidate_sum) <= m:
                right = candidate_sum - 1
                res = candidate_sum
            else:
                left = candidate_sum + 1
        
        return res
    def min_splits_sum(self, nums, target):
        curr_sum, num_splits = 0, 1

        for num in nums:
            if curr_sum + num <= target:
                curr_sum += num
            else:
                curr_sum = num
                num_splits += 1
        return num_splits

```


