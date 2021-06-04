class Solution:
    def jump(self, nums: List[int]) -> int:
        if not nums or len(nums) == 0:
            return 0
        dp = [float("inf") for _ in nums]
        dp[0] = 0
        for i in range(1, len(nums)):
            for j in range(i):
                if nums[j] >= i - j:
                    dp[i] = min(dp[i], dp[j] + 1)
        return dp[-1]
     
    def jump(self, nums: List[int]) -> int:
        if not nums or len(nums) == 0:
            return 0
        dp = [float("inf") for _ in nums]
        dp[0] = 0
        for i in range(1, len(nums)):
            dp[i] = min([dp[j] + 1 for j in range(i) if nums[j] >= i - j])
            
        return dp[-1]
    
