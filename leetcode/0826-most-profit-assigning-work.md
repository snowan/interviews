## Problem: 826. Most Profit Assigning Work

https://leetcode.com/problems/most-profit-assigning-work/description/

You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:

difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if three workers attempt the same job that pays (, then the total profit will be {. If a worker cannot complete any job, their profit is $0.
Return the maximum profit we can achieve after assigning the workers to the jobs.


 Example 1:

 Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 Output: 100
 Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.


Example 2:

 Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 Output: 0
  

  Constraints:

  n == difficulty.length
  n == profit.length
  m == worker.length
  1 <= n, m <= 104
  1 <= difficulty[i], profit[i], worker[i] <= 105})

## Solution

for each worker, always pick the max_pay job. 

1. zip(difficulty, profit) to job and sort 
2. sort worker
3. loop worker, 2 pointers, keep track of max pay job for current work when work >= jobs[i][0] (difficulty). benefits of sort worker, no need to scan smaller difficulty jobs.


**Time Complexity:** O(nlogn)
**Space Complexity:** O(n)

```python
class Solution:
    def maxProfitAssignment(self, difficulty: List[int], profit: List[int], worker: List[int]) -> int:
        if not worker:
            return 0
        jobs = sorted(zip(difficulty, profit))
        worker = sorted(worker)
        res, idx, max_pay = 0, 0 , 0
        for w in worker:
            while idx < len(jobs) and w >= jobs[idx][0]:
                max_pay = max(max_pay, jobs[idx][1])
                idx += 1
            res += max_pay

        return res

```

