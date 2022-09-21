## Problems: 985. Sum of Even Numbers After Queries

https://leetcode.com/problems/sum-of-even-numbers-after-queries/

You are given an integer array nums and an array queries where queries[i] = [vali, indexi].

For each query i, first, apply nums[indexi] = nums[indexi] + vali, then print the sum of the even values of nums.

Return an integer array answer where answer[i] is the answer to the ith query.

 

 Example 1:

 Input: nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
 Output: [8,6,2,4]
 Explanation: At the beginning, the array is [1,2,3,4].
 After adding 1 to nums[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
 After adding -3 to nums[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
 After adding -4 to nums[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
 After adding 2 to nums[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 Example 2:

 Input: nums = [1], queries = [[4,0]]
 Output: [0]
  

  Constraints:

  1 <= nums.length <= 104
  -104 <= nums[i] <= 104
  1 <= queries.length <= 104
  -104 <= vali <= 104
  0 <= indexi < nums.length


## Solution

first sum of even numbers from nums array, and then maitain the array for each query. rolling update even sum,
- if nums[idx] is even, even_sum -= nums[idx]
- update nums[idx] += query_val
- check updated nums[idx] is even, even_sum += nums[idx]
- append even_sum to res 

**Time Complexity:** O(m+n) - m is the length of nums, n is the length of queries
**Space Complexity:** O(1)


```python
class Solution:
    def sumEvenAfterQueries(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        if not nums or not queries:
            return []
        even_sum = sum(num for num in nums if num % 2 == 0)

        res = []
        for val, idx in queries:
            if nums[idx] % 2 == 0:
                even_sum -= nums[idx]
            nums[idx] += val
            if nums[idx] % 2 == 0:
                even_sum += nums[idx]
            res.append(even_sum)

        return res

```
