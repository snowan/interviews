class Solution:
    def checkPossibility(self, nums: List[int]) -> bool:
        if not nums or len(nums) < 2:
            return True
        count = 0
        for i in range(1, len(nums)):
            if nums[i - 1] > nums[i]:
                count += 1
                if i < 2 or nums[i - 2] <= nums[i]:
                    nums[i - 1] = nums[i]
                else:
                    nums[i] = nums[i - 1]
            if count > 1:
                return False
        return True
                
            
