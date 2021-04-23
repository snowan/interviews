class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        if not wall or len(wall) == 0:
            return 0
        pre_sum_map, max_sum = {}, 0
        for w in wall:
            pre_sum = 0;
            for c in range(len(w) - 1):
                pre_sum += w[c]
                pre_sum_map[pre_sum] = pre_sum_map.get(pre_sum, 0) + 1
                max_sum = max(max_sum, pre_sum_map.get(pre_sum))
        
        return len(wall) - max_sum
                
