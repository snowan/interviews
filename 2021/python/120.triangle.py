class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        if not triangle or len(triangle) == 0:
            return 0
        below_row = triangle[-1]
        rows = len(triangle)
        for r in reversed(range(rows - 1)):
            for c in range(0, r + 1):
                below_row[c] = min(below_row[c], below_row[c + 1]) + triangle[r][c]
        return below_row[0]
