import collections
import functools
import sys
from typing import List
from fractions import Fraction


class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        if not points:
            return 0
        res = 0
        for i, point in enumerate(points):
            dup = 1
            slope_count = collections.defaultdict(int, {None: 0})
            for j in range(i + 1, len(points)):
                point2 = points[j]
                if point2 == point:
                    dup += 1
                    continue
                slope = Fraction(point2[1] - point[1], point2[0] -
                                 point[0]) if point2[0] != point[0] else sys.maxsize
                slope_count[slope] += 1
            print(slope_count)
            res = max([res] + [(dup + count)
                               for count in slope_count.values()])
        return res
