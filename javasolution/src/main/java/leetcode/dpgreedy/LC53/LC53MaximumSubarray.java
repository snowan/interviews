package leetcode.dpgreedy.LC53;

public class LC53MaximumSubarray {
  public int maxSubArrayDividConquer(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    return helper(nums, 0, nums.length - 1);
  }
  private int helper(int[] nums, int l, int r) {
    if (l > r) return Integer.MIN_VALUE;
    int mid = (l + r) >>> 1;
    int left = helper(nums, l, mid - 1);
    int right = helper(nums, mid + 1, r);
    int leftMaxSum = 0;
    int sum = 0;
    for (int i = mid - 1; i >= l; i--) {
      sum += nums[i];
      leftMaxSum = Math.max(leftMaxSum, sum);
    }
    int rightMaxSum = 0;
    sum = 0;
    for (int i = mid + 1; i <= r; i++) {
      sum += nums[i];
      rightMaxSum = Math.max(sum, rightMaxSum);
    }
    System.out.println("(" + l + ", " + r + ", " + mid + "), " + "middle : " + nums[mid]);
    System.out.println("crossSum: " + (leftMaxSum + rightMaxSum + nums[mid]) + ", left= " + left + ", right= " + right);
    return Math.max(leftMaxSum + rightMaxSum + nums[mid], Math.max(left, right));
  }

  public int maximumSubarr(int[] nums) {
    int len = nums.length;
    int[] prefixSum = new int[len + 1];
    // calculate prefixsum
    for (int i = 1; i <= len; i++) {
      prefixSum[i] = prefixSum[i-1] + nums[i - 1];
    }
    int maxSum = nums[0];
    // subarray range [l,r]
    int sum = 0;
    for (int l = 1; l <= len; l++) {
      for (int r = l; r <= len; r++) {
        sum = l == r ? nums[l - 1] : (prefixSum[r] - prefixSum[l - 1]);
        maxSum = Math.max(maxSum, sum);
      }
    }
    return maxSum;
  }

  /**
   * solution #3: DP
   */
  public int maxSubArray(int[] nums) {
    int currMaxSum = nums[0];
    int maxSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      currMaxSum = Math.max(currMaxSum + nums[i], nums[i]);
      maxSum = Math.max(maxSum, currMaxSum);
      System.out.println("currMaxSum: " + currMaxSum + ", maxSum: " + maxSum);
    }
    return maxSum;
  }

  public static void main(String[] args) {
    LC53MaximumSubarray test = new LC53MaximumSubarray();
//    System.out.println(test.maxSubArrayDividConquer(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    System.out.println(test.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
  }
}
