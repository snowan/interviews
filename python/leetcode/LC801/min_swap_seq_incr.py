
class Solution:
    def minSwap(self, A: List[int], B: List[int]) -> int:
        n = len(A)
        swap, no_swap = [n] * n, [n] * n
        swap[0] = 1
        no_swap[0] = 0
        for i in range(1, n):
            if A[i] > A[i - 1] and B[i] > B[i - 1]:
                swap[i] = swap[i - 1] + 1
                no_swap[i] = no_swap[i - 1]
            if A[i] > B[i - 1] and B[i] > A[i - 1]:
                swap[i] = min(swap[i], no_swap[i - 1] + 1)
                no_swap[i] = min(no_swap[i], swap[i - 1])
                
        return min(swap[n - 1], no_swap[n - 1])