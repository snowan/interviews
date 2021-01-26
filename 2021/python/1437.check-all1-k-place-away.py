"""
1437. Check If All 1's Are at Least Length K Places Away

Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k places away from each other, otherwise return False.

Example 1:

Input: nums = [1,0,0,0,1,0,0,1], k = 2
Output: true
Explanation: Each of the 1s are at least 2 places away from each other.

Example 2:

Input: nums = [1,0,0,1,0,1], k = 2
Output: false
Explanation: The second 1 and third 1 are only one apart from each other.
Example 3:

Input: nums = [1,1,1,1,1], k = 0
Output: true
Example 4:

Input: nums = [0,1,0,1], k = 1
Output: true


Constraints:

1 <= nums.length <= 105
0 <= k <= nums.length
nums[i] is 0 or 1
"""
def kLengthApart(nums: List[int], k: int) -> bool:
    if not nums or len(nums) == 0 or k == 0:
        return True
    # init count = k to cover first 1 case
    count = k
    for num in nums:
        if num == 1:
            # check zero counts
            if count < k:
                return False
            # reset count = 0
        else:
            # count zeros
            count += 1
    return True
