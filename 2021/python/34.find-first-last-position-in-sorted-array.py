class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if not nums or len(nums) == 0:
            return [-1, -1]
        def binarySearch(num: int):
            lo, hi = 0, len(nums)
            while lo < hi:
                mid = lo + (hi - lo) // 2
                if nums[mid] >= num:
                    hi = mid
                else:
                    lo = mid + 1
            return lo
        first = binarySearch(target)
        if first == len(nums) or nums[first] != target:
            return [-1, -1]
        last = binarySearch(target + 1) - 1
        return [first, last]
            
