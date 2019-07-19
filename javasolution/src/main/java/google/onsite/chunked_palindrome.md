## Chunked Palindrome

### Problem
```
Normal palindrome is defined as a string that reads the same backwards as forwards, for example "abccba".
Chunked palindrome is defined as a string that you can split into chunks and it will form a palindrome.
For example, "volvo". You can split it into (vo)(l)(vo). Let A = "vo", B = "l", so the original string is ABA which is a palindrome.

Given a string str, find the maximum number of chunks we can split that string to get a chuncked palindrome.

Example 1:

Input: "valve"
Output: 1
Explanation: You can't split it into multiple chunks so just return 1 (valve)
Example 2:

Input: "voabcvo"
Output: 3
Explanation: (vo)(abc)(vo)
Example 3:

Input: "vovo"
Output: 2
Explanation: (vo)(vo)
Example 4:

Input: "volvolvo"
Output: 5
Explanation: (vo)(l)(vo)(l)(vo)
Example 5:

Input: "volvol"
Output: 2
Explanation: (vol)(vol)
Example 6:

Input: "aaaaaa"
Output: 6
Explanation: We can split it into (aaa)(aaa), but the optimal split should be (a)(a)(a)(a)(a)(a)
Questions from the same interview:

Product of K consecutive numbers
```

### 题意
给定一个字符串，让求出最多可以切成分块的回文，每一个切分可以是一个子字符串，或者单个字符。

例如， "aaaaaa"， 可以切分成 "aaa)(aaa)" - 2， 但是这里要求最多分块， 这样就是最多的"(a)(a)(a)(a)(a)(a)" - 6

### 解题
#### 解法一，
分析， 满足回文字符串的要求是，从前读和从后读是一样的， 例如， "aba"， "anna"，"madam" 等

那么这里就可以分别从前（l）和从后（r）扫描，l 和 r 记录前后扫描的index， 然后用pre_l and pre_r 记录前一次扫描到的可以分块的回文位置，

如果子字符串相同（substr（pre_l, l + 1) 这里包含边界），结果 +2，继续扫描。

如果最后剩余一个（奇数个chunk）， 那么直接结果 +1， 否则偶数个chunk， 直接返回结果

例如："volvolvo"

![alt text](../.../../../../resources/img/chunked_palindrom.png)

**Java 代码实现**
```java
public static int maxChunkedPalindrome2(String s) {
    if (s == null || s.length() == 0) return 0;
    int l = 0;
    int r = s.length() - 1;
    int max = 0;
    int preL = l;
    int preR = r;
    while (l < r) {
      String prefix = s.substring(preL, l + 1); // include right
      String sufix = s.substring(r, preR + 1);
      if (prefix.equals(sufix)) {
        preL = l + 1;
        preR = r - 1;
        max += 2;
      }
      l++;
      r--;
    }
    if (preL <= preR) max++;
    System.out.println("max chunk palindrom: " + max);
    return max;
  }
```

#### 解法二
