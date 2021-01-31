"""
1675. Minimize Deviation in Array

You are given an array nums of n positive integers.

You can perform two types of operations on any element of the array any number of times:

If the element is even, divide it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
If the element is odd, multiply it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
The deviation of the array is the maximum difference between any two elements in the array.

Return the minimum deviation the array can have after performing some number of operations.

Example 1:

Input: nums = [1,2,3,4]
Output: 1
Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
Example 2:

Input: nums = [4,1,5,20,3]
Output: 3
Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
Example 3:

Input: nums = [2,10,8]
Output: 3
 

Constraints:

n == nums.length
2 <= n <= 105
1 <= nums[i] <= 109
"""
def minimumDeviation(nums: List[int]) -> int:
        if not nums or len(nums) < 2:
            return 0
        # 1. turn all odd elements into even and enqueue to a max heap 
        # 2. find min and max value
        small = -nums[0] if nums[0] % 2 == 0 else -nums[0] * 2
        large = small
        pq = []
        for num in nums:
            if num % 2 == 1:
                num *= 2
            large = max(large, -num)
            small = min(small, -num)
            heapq.heappush(pq, -num)
        # 3. every time poll lartest element from pq, divide 2 and put it back to max heap 
        # 4. return until max element is odd number
        res = large - small
        while True:
            #print(heapq.nlargest(4, pq))
            top = heapq.heappop(pq)
            res = min(res, abs(top - large))
            if top % 2 == 1:
                return res
            top //= 2
            # maintain smallest element
            if top > large:
                large = top
            heapq.heappush(pq, top)
        return res
