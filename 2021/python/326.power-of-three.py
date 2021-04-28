class Solution:
   
    # 1. loop
    def isPowerOfThree(self, n: int) -> bool:
        if n < 1:
            return False
        while n % 3 == 0:
            n //= 3
        return n == 1
     
    # 2. recursive
    def isPowerOfThree(self, n: int) -> bool:
        if n < 1:
            return False
        if n == 1:
            return True
        if n % 3 == 0:
            return self.isPowerOfThree(n // 3)
        return False
    
    # 3. math
    ## take advantage of highest limitation is 3**19 = 1162261467 since Integer has range <2147483648, so if 1162261467 % n == 0, then True
    def isPowerOfThree(self, n: int) -> bool:
        return n > 0 and 1162261467 % n == 0
