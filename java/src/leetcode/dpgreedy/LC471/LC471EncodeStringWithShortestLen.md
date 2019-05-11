### 471. Encode String with Shortest Length
---
*Solution:* DP

`dp[i][j]` - represents substring between index `[i, j]`,
when `len < 5` (substring length from `i` to `j` (`j - i + 1 < 5`)) `dp[i][j] = s.substring[i, j]`.
when `len >= 5`, `dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j]) (i <= k < j)`.
and for current substring, check whether it can reform into new shorter length string,
if `newStr.length < dp[i][j].lenth`, replace `dp[i][j] = newStr`.

reform new String, traverse current substring (curr), get `substr[0, k]`,
current substring replace all with `substr[0,k]` with `""`, check current substring is `""`.
if true, then we can calculate the number of substr, and add `"[""` and `"]"`, so
`newStr = (current substring).length / substr.length + "[" + dp[i][i + k] + "]"`
if `newStr.length < dp[i][j].length`, then `dp[i][j] = newStr`.

`return dp[0][len - 1]`

for example: 
**String: "aabcaabcd"**
```
from observation, we can get
when l < 5, dp[i][j] = substring(i, j) (j - i <= l)
when l = 5, 
    i = 0, j = 4, substring = "aabca", dp[0][4] = "aabca"
        k = 0, (dp[0][0] + dp[1][4]).length == substring.length, skip
        k = 1, (dp[0][1] + dp[2][4]).length == substring.length, skip
        ...
        k = 3, (dp[0][3] + dp[4][4]).length == substring.length, skip
        check whether substring can be reform new string, no, skip 
    i = 1, j = 5, substring = "abcaa", dp[1][4] = "abcaa"
        k = 1, skip
        ...
        k = 4, (dp[1][4] + d[5][5).length == substring.length, skip
    i = 2, j = 6, substring = "bcaab" 
    ...
when l = 6, 
    i = 0, j = 5, substring = "aabcaa", dp[0][5] = "aabcaa"
    ...
when l = 7,
    i = 0, j = 6, substring = "aabcaab", dp[0][6] = "aabcaab"
    ...
when l = 8,
    i = 0, j = 7, substring = "aabcaabc", dp[0][7] = "aabcaabc"
        k = 0, (dp[0][0] + dp[1][7]) == dp[0][7], skip
        ...
    check reform new string, substring ("aabcaabc"), len = 8
        k = 0, firstSub = "a", check replace all "a" with "", "bcbc" != "", skip
        k = 1, firstsub = "aa", replace all "aa" with "", "bcbc" != "", skip
        k = 2, fisrtsub = "aab", replace all "aab" with "", "cc" != "", skip
        k = 3, firstsub = "aabc", replace all "aabc" with "", "" == "", reform new string
            newStr = len / (k + 1) = 2 + "[" + dp[i][i + k] (dp[0][3]) + "]" = "2[aabc]"
            2[aabc].length < 8, dp[0][7] = "2[aabc]"
        k = 4, firstsub = "aabca", 
        ...
when l = 9, 
    i = 0, j = 8, substring = "aabcaabcd", dp[0][8] = "aabcaabcd"
        k = 0, skip
        ...
        k = 7, dp[0][7] + dp[8][8] = "2[aabc]d".length < dp[0][8].length
            dp[0][8] = "2[aabc]d"
       
return dp[0][8]
```

| j |       | 0   |  1   | 2     |   3    | 4       | 5       | 6          | 7         | 8          |
|---|---|---|---|---|---|---|---|---|---|---|
| **i** | 0 | "a" | "aa" | "aab" | "aabc" | "aabca" | "aabcaa"| "aabcaab"  | "2[aabc]" | "2[aabc]d" |
|       | 1 |     | "a"  | "ab"  | "abc"  | "abca"  | "abcaa" | "abcaab"   | "abcaabc" | "abcaabcd" |
|       | 2 |     |      | "b"   | "bc"   | "bca"   |  "bcaa" | "bcaab"    | "bcaabc"  | "bcaabcd"  |
|       | 3 |     |      |       | "c"    | "ca"    | "caa"   | "caab"     | "caabc"   | "caabcd"   |
|       | 4 |     |      |       |        | "a"     | "aa"    | "aab"      | "aabc"    | "aabcd"    |
|       | 5 |     |      |       |        |         | "a"     | "ab"       | "abc"     | "abcd"     |
|       | 6 |     |      |       |        |         |         | "b"        | "bc"      | "bcd"      |
|       | 7 |     |      |       |        |         |         |            | "c"       | "cd"       |
|       | 8 |     |      |       |        |         |         |            |           | "d"        |
