class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        int res = 0;
        int sum = 0;
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] - A[i - 1] == A[i + 1] - A[i]) {
                sum++;
                res += sum;
            } else {
                sum = 0;
            }
        }
        
        return res;
    }
}
