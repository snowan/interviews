class Solution {
    public boolean reorderedPowerOf2(int N) {
        // power of 2, total 2^0 .... 2^31 
        // so compute all 2^n, and compare with N, if any 2^n equals to N, then return true. 
        // equals can use : sort all N digits, or compare whether has the same digits
        int[] counts = counter(N);
        for (int i = 0; i < 32; i++) {
            int[] pow2Count = counter((int)(1 << i));
            if (Arrays.equals(counts, pow2Count)) return true;
        }
        return false;
        
    }
    private int[] counter(int num) {
        int[] counts = new int[10];
        while (num > 0) {
            counts[num % 10]++;
            num /= 10;
        }
        return counts;
    }
}
