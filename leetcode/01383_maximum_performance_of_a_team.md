## Problem: 1383. Maximum Performance of a Team

https://leetcode.com/problems/maximum-performance-of-a-team/

You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.

Choose at most k different engineers out of the n engineers to form a team with the maximum performance.

The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.

Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.

 

 Example 1:

 Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 Output: 60
 Explanation: 
 We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.

 Example 2:

 Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 Output: 68
 Explanation:
 This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 
 Example 3:

 Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 Output: 72
  

  Constraints:

  1 <= k <= n <= 105
  speed.length == n
  efficiency.length == n
  1 <= speed[i] <= 105
  1 <= efficiency[i] <= 108


## Solution
In order to get maximum performance, how to maintain min efficiency and sum of speed for k engineers? since efficiency requires min, we can sort with efficiency in descending order.
and in window of k engineers (i, i + k) -> (i+k)th engineer will have min efficiency, and keep rolling update sum of k engineers, in order to keep max performance, we just need to 
keep track of max speed sum, then (min(efficiency) * max(speedSum)) will be the max performance. we can use heap (min heap) to record speed, rolling update speed by removing the smallest on the top. 

1. zip(efficiency, speed) into engineers
2. sort by efficiency with descending order
3. min heap to record speed, keep k element
4. rolling update max performance 

```python
class Solution:
    def maxPerformance(self, n: int, speed: List[int], efficiency: List[int], k: int) -> int:
        if not speed or not efficiency:
            return 0
        speedSum, res = 0, 0
        minHeap = []
        # sort (efficiency, speed) with descending order or efficiency 
        engineers = list(zip(efficiency, speed))
        engineers.sort(reverse=True)

        for ef, sp in engineers:
            heappush(minHeap, sp)
            speedSum += sp
            # maintain k speed in minHeap, and remove min speed from top
            if len(minHeap) > k:
                speedSum -= heappop(minHeap)
            res = max(res, ef * speedSum)

        return res % 1_000_000_007

```
