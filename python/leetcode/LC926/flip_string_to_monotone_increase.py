class Solution:
    def minFlipsMonoIncr(self, S: str) -> int:
        if len(S) < 3: return 0
        countZero, countOne = 0, 0
        for i in range(len(S)):
            countOne = min(countZero, countOne)
            if S[i] == '0':
                countOne += 1
            else:
                countZero += 1
        return min(countOne, countZero); 
