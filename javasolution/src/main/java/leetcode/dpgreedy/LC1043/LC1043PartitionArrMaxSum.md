### [1043. Partition Array for Maximum Sum](./LC1043PartitionArrMaxSum.java)
---

#### Approach #1. DP
len - length of input string, `dp[len]`

`dp[i]` - max sum of `A[0], ..., A[i - 1]`

For `j = 1 .. k` that keeps everything in bounds, `dp[i]` is the maximum of `dp[i-j] + max(A[i-1], ..., A[i-j]) * j`

`dp[i] = max(dp[i], dp[i - j] + j * max(A[i - j + 1], ..., A[i])`

Result: `dp[len - 1]`

for example:
 ```
 A = [1, 8, 7, 9], K = 3
 i = 0,
    j = 1, dp[0] = max(0 + 1 * 1) = 1;
 i = 1, dp[1] = 16
    j = 1, dp[1] = max(dp[1], dp[0] + 8 * 1) = 9
    j = 2, dp[1] = max(dp[1], 0 + 8 * 2) = 16 
 i = 2, dp[2] = 24
    j = 1, dp[2] = max(dp[2], dp[1] + 7 * 1) = 23
    j = 2, dp[2] = max(dp[2], dp[0] + 8 * 2) = 17 < 23 = 23
    j = 3, dp[2] = max(dp[2], 8 * 3) = 24
 i = 3, dp[3] = 
    j = 1, dp[3] = max(dp[3], dp[2] + 9 * 1) = 33
    j = 2, dp[3] = max(dp[3], dp[1] + 9 * 2) = 34
    j = 3, dp[3] = max(dp[3], dp[0] + 9 * 3) = 28 < 34 = 34
    
 dp[3] = 34
```

TC: O(N * K)
SC: O(N)

```java
public static int maxSumAfterPartitioning(int[] A, int K) {
    if (A == null || A.length == 0) return 0;
    int len = A.length;
    int[] dp = new int[len];
    for (int i = 0; i < len; i++) {
      int maxVal = A[i];
      for (int j = 1; j <= K && i - j + 1 >= 0; j++) {
        maxVal = Math.max(maxVal, A[i - j + 1]);
        int maxSum = (i >= j ? dp[i - j] : 0) + maxVal * j;
        dp[i] = Math.max(maxSum, dp[i]);
      }
    }
    System.out.println("Result= " + dp[len - 1]);
    return dp[len - 1];
  }
```


#### Approach #2, rescursive + memorization

```java
public static int maxSumPartition(int[] A, int K) {
    if (A == null || A.length == 0) return 0;
    int max = helper(A, K, 0, new HashMap<>());
    System.out.println("Recursive result= " + max);
    return max;
  }

  private static int helper(int[] A, int K, int idx, Map<Integer, Integer> map) {
    if (idx == A.length) return 0;
    if (map.containsKey(idx)) return map.get(idx);
    int maxSum = 0;
    int maxVal = A[idx];
    for (int i = idx; i <= idx + K - 1 && i < A.length; i++) {
      maxVal = Math.max(maxVal, A[i]);
      maxSum = Math.max(maxSum, helper(A, K, i + 1, map) + maxVal * (i - idx + 1));
    }
    map.put(idx, maxSum);
    return maxSum;
  }
```