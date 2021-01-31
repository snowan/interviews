def minimumDeviation(nums: List[int]) -> int:
        if not nums or len(nums) < 2:
            return 0
        # 1. turn all odd elements into even and enqueue to a max heap 
        # 2. find min and max value
        small = -nums[0] if nums[0] % 2 == 0 else -nums[0] * 2
        large = small
        pq = []
        for num in nums:
            if num % 2 == 1:
                num *= 2
            large = max(large, -num)
            small = min(small, -num)
            heapq.heappush(pq, -num)
        # 3. every time poll lartest element from pq, divide 2 and put it back to max heap 
        # 4. return until max element is odd number
        res = large - small
        while True:
            #print(heapq.nlargest(4, pq))
            top = heapq.heappop(pq)
            res = min(res, abs(top - large))
            if top % 2 == 1:
                return res
            top //= 2
            # maintain smallest element
            if top > large:
                large = top
            heapq.heappush(pq, top)
        return res
