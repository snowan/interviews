class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n < 3) return 1;
        int prevFst = 1;
        int prevSnd = 1;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = prevFst + prevSnd;
            prevFst = prevSnd;
            prevSnd = res;
        }
        return res;
    }
    
    
    public int fib2(int n) {
      if (n == 0) return 0;
      if (n == 1) return 1;
      
      return fib2(n - 1) + fib2(n - 2);
    }
}
