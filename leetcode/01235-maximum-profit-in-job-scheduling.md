## Problem: 1235. Maximum Profit in Job Scheduling

https://leetcode.com/problems/maximum-profit-in-job-scheduling/

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.


Example 1:

Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.

Example 3:

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104


## Solution
dp problem, similar to change coin. 

dp[i] -- maximum profit at ith job
1. do not pick ith job, `dp[i] = dp[i-1]`
2. pick ith job, find previous job ends before current job starts. `dp[i] = dp[prev] + profit[i]`

in order to efficiently find previous job ends before current job starts, we can sort by job endtime, and do binary search. 

first zip (startTime, endTime, profit) and sort by endTime

`jobs = sorted(zip(startTime, endTime, profit), key=lambda job: job[1])`

**Time Complexity: ** O(nlogn) 
**Space Complexiy: ** O(n)

```python
class Solution:
    def jobScheduling(self, startTime: List[int], endTime: List[int], profit: List[int]) -> int:
        if not startTime:
            return 0
        jobs = sorted(zip(startTime, endTime, profit), key=lambda job: job[1])
        n = len(jobs)
        dp = [jobs[0][2]] + [0] * (n - 1)
        for i in range(1, n):
            # do not pick up ith job
            dp[i] = dp[i - 1]
            # pick up ith job, get previous job ends time < current job (ith) starts time, binary search 
            prevIdx = self.bs(jobs, jobs[i][0]) 
            prev = 0 if prevIdx == -1 else dp[prevIdx]
            dp[i] = max(dp[i], prev + jobs[i][2])

        return dp[n-1]
        
    def bs(self, jobs, target):
        l, r = 0, len(jobs)
        while l < r:
          mid = (l + r) // 2
          if jobs[mid][1] > target:
              r = mid
          else:
              l = mid + 1
        return l - 1

```


