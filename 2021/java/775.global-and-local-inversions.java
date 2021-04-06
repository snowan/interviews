class Solution {
    // local inversion belongs to global inversion.
    // to find out whether local inversion == global inversion, just need to check max(A[i]) > A[i+2]
    // if max(A[i]) > A[i + 2], then # of global inversion > # of local inversion
    public boolean isIdealPermutation(int[] A) {
        int max = A[0];
        for (int i = 0; i < A.length - 2; i++) {
            max = Math.max(max, A[i]);
            if (max > A[i + 2]) return false; 
        }
        return true;
    }
}
