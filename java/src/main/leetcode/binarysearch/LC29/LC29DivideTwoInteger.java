package main.leetcode.binarysearch.LC29;

public class LC29DivideTwoInteger {
  public static int divide(int dividend, int divisor) {
    if (dividend == 0) return 0;
    if (divisor == 1) return dividend;
    if (divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
    boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
    long ldividend = Math.abs((long)dividend);
    long ldivisor = Math.abs((long)divisor);
    long res = 0;
    long multi = 1;
    long sub = ldivisor;
    while (ldividend >= ldivisor) {
      if (ldividend >= sub) {
        res += multi;
        ldividend -= sub;
        sub <<= 1; // sub *= 2;
        multi <<= 1; // multi *= 2;
      } else {
        sub >>= 1; // sub /= 2;
        multi >>= 1; // multi /= 2;
      }
    }
    return isPositive ? (int)res : (int)-res;
  }
}
