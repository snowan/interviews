class Solution {
    public int minimumDeviation(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 1. all odd elements *2 become even,
        // 2. put all even elements into treeset, de-dup
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums) {
            if (n % 2 == 1) n *= 2;
            set.add(n);
        }
        if (set.size() == 1) return 0;
        int res = Integer.MAX_VALUE;
        // 3. retrieve min and max element from set and compare, max / 2 and put back into set
        // 4. when set only have 1 element return 0 or lasgest element is odd, return res
        while (true) {
            if (set.size() == 1) return 0;
            int min = set.pollFirst();
            int max = set.pollLast();
            res = Math.min(res, max - min);
            if (max % 2 == 1) return res;
            set.add(min);
            set.add(max / 2);
        }
    }
}
