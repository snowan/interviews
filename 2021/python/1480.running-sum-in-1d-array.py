class Solution:
    def runningSum(self, nums: List[int]) -> List[int]:
        if not nums or len(nums) == 0:
            return nums
        res = []
        res.append(nums[0])
        for i in range(1, len(nums)):
            res.append(res[-1] + nums[i])
        return res
