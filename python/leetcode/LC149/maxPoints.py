import collections
import functools
import sys
from typing import List
from math import gcd

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
                dx = point2[0] - point[0]
                dy = point2[1] - point[1]
                if dx == 0:
                    slope = sys.maxsize
                elif dy == 0:
                    slope = 0
                else:
                    g = gcd(abs(dy), abs(dx))
                    slope = '{}-{}-{}'.format((dx > 0) is (dy > 0), abs(dy) / g, abs(dx) / g)
                slope_count[slope] += 1
            print(slope_count)
            res = max([res] + [(dup + count)
                               for count in slope_count.values()])
        return res
