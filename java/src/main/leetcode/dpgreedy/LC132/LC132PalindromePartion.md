### [132 Palindrome Partitioning II](./LC132PalindromePartitionII.java)
---

### Solution: DP

#### Approach #1

`dp[i][j]` - min cut substring from `i` to `j`

init: 

`dp[i][i] = 0`, since `substring[i,i]` is palindrome, no need to cut

and `dp[i][j] = len + 1`

transition:

when substring[i,j] is palindrome, `dp[i][j] = 0`

otherwise `dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + 1) (i <= k < j)`,

return `dp[0][len-1]`

for example: "abcbd"

`len = 5`, `dp[5][5]`, fill `dp[i][j] = len + 1`
```
when len = 1, 
    dp[i][i] = 0, no need to cut
when len = 2, 
    i = 0, j = 1, !isPalindrome(0,1), dp[0][1] = 1
    i = 1, j = 2, !isPalindrome(0,1), dp[1][2] = min(dp[1][2], dp[1][1] + dp[2][2] + 1)
    ...
    
when len = 3,
    i = 0, j = 2, !isPalindrome(0,2), dp[0][2] = 2
    ...

when len = 4,
    i = 0, j = 3, s[i] != s[j], dp[0][3] = max(dp[1][3], dp[0][2])
    i = 1, j = 4, s[i] == s[j], dp[1][4] = dp[2][3] + 2

when len = 5,
    i = 0, j = 4, !isPalindrome(0,4)
        k = 0, dp[0][4] = min(dp[0][4], dp[0][0] + dp[1][4] + 1)
        k = 1, dp[0][4] = min(dp[0][4], dp[0][1] + dp[2][4] + 1)
        k = 2, dp[0][4] = min(dp[0][4], dp[0][2] + dp[3][4] + 1)
        k = 3, dp[0][4] = min(dp[0][4], dp[0][3] + dp[4][4] + 1)
```


| j |       | 0 | 1 | 2 | 3 | 4 |
|---|---|---|---|---|---|---|
| **i** | 0 | 0 | 1 | 2 | 3 | 2 |  
|       | 1 |   | 0 | 1 | 0 | 1 |  
|       | 2 |   |   | 0 | 1 | 2 |  
|       | 3 |   |   |   | 0 | 1 |  
|       | 4 |   |   |   |   | 0 | 


Time complexity: `O(n ^ 4)` - n is the length of input string
Space complexity: `O(n ^ 2)`


#### Approach #2.
[reference solution from LC discussion @yavinci
](https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36))

From above approach #1, time complexity is too high, we can remember the state of palindrome for substring[i,j,

This can be solved by two points:

First point is: 

`cut[i]` is the minimum of `cut[j - 1] + 1 (j <= i)`, if `substring[j, i]` is palindrome.
If `[j, i]` is palindrome, `[j + 1, i - 1]` is palindrome, and `c[j] == c[i]`.

The 2nd point reminds us of using dp (caching).
```
a   b   a   |   c  c
                j  i
       j-1  |  [j, i] is palindrome
   cut(j-1) +  1

```

Time complexity: `O(n^2)`
Space complexity:` O(n^2)`