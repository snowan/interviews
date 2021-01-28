class Solution {
    private static final int MOD = 1000000007;
    public int concatenatedBinary(int n) {
        long res = 0;
        int mods = 1;
        for (int i = n; i > 0; i--) {
            int num = i;
            while (num > 0) {
                if (num % 2 == 1) {
                    res = (res + mods) % MOD;
                }
                num >>= 1;
                mods = (mods << 1) % MOD;
            }
        }
        return (int)res;
    }
}
