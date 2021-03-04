class Solution {
  // math : [0, n] sum = n*(n+1)/2
  public int missingNumber(int[] nums) {
      int n = nums.length;
      int expectSum = n * (n + 1) / 2;
      int actualSum = 0;
      for (int num : nums) actualSum += num;
      return expectSum - actualSum;
  }

  // hashSet
  public int missingNumber2(int[] nums) {
    int n = nums.length;
    Set<Integer> set = new HashSet<>();
    for (int num : nums) set.add(num);
    for (int i = 0; i <= n; i++) {
      if (!set.contains(i)) return i;
    }
    return n;
  }
  
  // bit i ^ i = 0, 0 ^ i = i
  public int missingNumber3(int[] nums) {
    int n = nums.length;
    int missing = n;
    for (int i = 0; i < n; i++) {
      missing ^= i ^ nums[i];
    }
    return missing;
  }
    
}
