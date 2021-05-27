class Solution:
    # discussion: https://leetcode.com/problems/super-palindromes/discuss/174835/tell-you-how-to-get-all-super-palindrome(detailed-explanation)
   
    def superpalindromesInRange(self, left: str, right: str) -> int:
        def isSuperpalInRange(i):
            if str(i**2) == str(i**2)[::-1] and l <= i**2 <= r:
                return True
            return False
        l, r = int(left), int(right)
        cnt = 0
        for i in [1,4,9]:
            if l <= i <= r:
                cnt += 1
        for i in range(1,10000):
            s = str(i)+str(i)[::-1]
            if isSuperpalInRange(int(s)):
                cnt += 1
            for j in range(10):
                s = str(i) + str(j) + str(i)[::-1]
                if isSuperpalInRange(int(s)):
                    cnt += 1
        return cnt
