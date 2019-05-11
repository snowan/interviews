 ### 516. Longest Palindromic Subsequence
 ---
 
 *Solution*: 
 
 DP + sliding window, start from `len = 2`, and keep (`right(j) - left(i) + 1 = len`), moving from left to right.
 for
 
  `s.charAt(i) == s.charAt(j)`, `dp[i][j] = dp[i + 1][j - 1] + 2` (when `len = 2`, `dp[i][j] = 2`)
  
  otherwise `dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])`
  
  result `dp[0][n - 1]`
   
    TC: O(n ^ 2) - n is the length of input string
    SC: O(n ^ 2)
  
for example: 
String: "bbacb"

n = 6, dp[5][5]
```
when len = 2, 
    i = 0, j = 1, s[i] == s[j], dp[0][1] = 2
    i = 1, j = 2, s[i] != s[j], dp[1][2] = max(dp[2][2], dp[1][1])
    i = 2, j = 3, s[i] != s[j], dp[2][3] = max(dp[3][3], dp[2][2])
    i = 3, j = 4, s[i] != s[j], dp[3][4] = max(dp[4][4], dp[3][3])
    
when len = 3,
    i = 0, j = 2, s[i] != s[j], dp[0][2] = max(dp[1][2], dp[0][1])
    i = 1, j = 3, s[i] != s[j], dp[1][3] = max(dp[2][3], dp[1][2])
    i = 2, j = 4, s[i] != s[j], dp[2][4] = max(dp[3][4], dp[2][3])

when len = 4,
    i = 0, j = 3, s[i] != s[j], dp[0][3] = max(dp[1][3], dp[0][2])
    i = 1, j = 4, s[i] == s[j], dp[1][4] = dp[2][3] + 2

when len = 5,
    i = 0, j = 4, s[i] == s[j], dp[0][4] = dp[1][3] + 2
```


| j |       | 0 | 1 | 2 | 3 | 4 |
|---|---|---|---|---|---|---|
| **i** | 0 | 1 | 2 | 2 | 2 | 3 |  
|       | 1 |   | 1 | 1 | 1 | 3 |  
|       | 2 |   |   | 1 | 1 | 1 |  
|       | 3 |   |   |   | 1 | 1 |  
|       | 4 |   |   |   |   | 1 | 
