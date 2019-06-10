import bisect
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        for i in reversed(range(len(nums) - 1)):
            if nums[i] < nums[i + 1]:
                l = sorted(nums[i + 1:])
                partition = bisect.bisect_right(l, nums[i])
                nums[i], l[partition] = l[partition], nums[i]
                nums[i + 1:] = l
                return
        nums.reverse()