"""
31. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
Example 4:

Input: nums = [1]
Output: [1]
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
"""
def nextPermutation(self, nums: List[int]) -> None:
    """
    Do not return anything, modify nums in-place instead.
    """
    if not nums or len(nums) <= 1:
        return
    l = len(nums) - 2
    # find right most element which breaks descending order
    while l >= 0 and nums[l] >= nums[l + 1]:
        l -= 1
    # if list no fully descending order, find right largest element and swap with left
    if l >= 0:
        r = len(nums) - 1
        while r >= 0 and nums[r] <= nums[l]:
            r -= 1
        # swap left and right
        nums[l], nums[r] = nums[r], nums[l]
    # reverse right part (from left)
    nums[l+1 : len(nums)] = nums[l+1 : len(nums)][::-1]
