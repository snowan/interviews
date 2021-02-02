public class Solution {
    // solution 1: using bitCount()
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
    
    // solution 2: using bit operation
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
