"""
1658.Minimum Operations to Reduce X to Zero


You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
"""

"""
Solution: 
think array as 3 parts: left most + middle + right most 
this question is asking to find minimum ops which sum(leftMost + rightMost) = x. instead of find min of prefix + suffix. think of finding maximum subarray which 
sum == sum(array) - x.
now the question becomes to find max subarray sum = target problem.
use map(val, index) + two pointers
"""
def minOperations(nums: List[int], x: int) -> int:
        target = sum(nums) - x
        if target == 0:
            return len(nums)
        size = len(nums)
        max_subarr = -1
        pre_sum = 0
        dic = {0 : -1}
        for i, num in enumerate(nums):
            pre_sum += num
            if (pre_sum - target) in dic:
                max_subarr = max(max_subarr, i - dic[pre_sum - target])
            dic[pre_sum] = i
        return -1 if max_subarr == -1 else size - max_subarr
