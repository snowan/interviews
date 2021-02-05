"""
594. Longest Harmonious Subsequence

We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.

Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.

A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.

Example 1:

Input: nums = [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].
Example 2:

Input: nums = [1,2,3,4]
Output: 2
Example 3:

Input: nums = [1,1,1,1]
Output: 0
 

Constraints:

1 <= nums.length <= 2 * 104
-109 <= nums[i] <= 109
"""
"""
solution:
1. count each element frequency
2. iterate list, search for num + 1, if exits, compare res vs counts(num) + counts(num + 1)
3. return res
"""
def findLHS(self, nums: List[int]) -> int:
    if not nums or len(nums) < 2:
        return 0
    #counts = dict((x in nums.count(x)) for x in set(nums)) -- not work
    counts = {}
    for num in nums:
        counts[num] = counts.get(num, 0) + 1
    res = 0
    for i, num in enumerate(nums):
        if num + 1 in nums:
            res = max(res, counts[num] + counts[num + 1])
    return res
