package leetcode.array.LC1151;

import java.util.Arrays;

public class LC1151MinSwapsAllOnes {
  public static int minSwaps(int[] data) {
    int len = data.length;
    int maxOnes = 0;
    int totalOnes = Arrays.stream(data).sum();
    int l = -1;
    int r = 0;
    int countOne = 0;
    while (l < len && r < len) {
      if (r - l <= totalOnes) {
        countOne = countOne + data[r++];
      } else {
        maxOnes = Math.max(maxOnes, countOne);
        countOne -= data[++l];
        countOne = countOne + data[r++];
      }
    }
    System.out.println("res: " + (totalOnes - maxOnes));
    return totalOnes - maxOnes;
  }

  public static void main(String[] args) {
    LC1151MinSwapsAllOnes.minSwaps(new int[]{1,0,1,0,1});
    LC1151MinSwapsAllOnes.minSwaps(new int[]{1,0,1,0,1,0,0,1,1,0,1});
  }
}
