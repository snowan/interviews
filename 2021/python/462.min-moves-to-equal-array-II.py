class Solution:
    # solutions:
    # 1. sort array
    # 2. get median element (min moves should be all elements move equals to the middle elements)
    # 3. calculate diff for each element compare to middle element
    def minMoves2(self, nums: List[int]) -> int:
        if not nums or len(nums) == 0:
            return 0
        res, size = 0, len(nums)
        mid = sorted(nums)[size // 2]
        for i in range(size):
            res += abs(nums[i] - mid)
        return res
        
        
        
