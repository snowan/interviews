class Solution:
    # thoughts:
    # transfer maximum sum to find min sum subarray of size (len(cardPoints) - k)
    # maximum sum = sum(carPoints) - minsumSubArr
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        if not cardPoints or len(cardPoints) == 0:
            return 0
        if len(cardPoints) <= k:
            return sum(cardPoints)
        size = len(cardPoints) - k
        # sliding window 
        minsumSubarr = currsum = sum(cardPoints[:size])
        for idx in range(k):
            currsum += cardPoints[size + idx] - cardPoints[idx]
            minsumSubarr = min(minsumSubarr, currsum)
        
        return sum(cardPoints) - minsumSubarr
