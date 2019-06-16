package leetcode.array.LC1089;

import java.util.Arrays;

public class LC1089MoveZeros {
  public static void duplicateZeros(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int len = arr.length;
    for (int i = 0; i < len - 1; i++) {
      if (arr[i] == 0) {
        moveNext(arr, i + 1);
        i++;
      }
    }
  }
  private static void moveNext(int[] arr, int start) {
    int len = arr.length;
    int prev = arr[start];
    arr[start] = 0;
    for (int i = start + 1; i < len; i++) {
      int tmp = arr[i];
      arr[i] = prev;
      prev = tmp;
    }
  }

  public void duplicateZeros2(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int zc = 0;
    int idx = 0;
    int len = arr.length;
    for (idx = 0; idx + zc < len; idx++) {
      if (arr[idx] == 0) zc++;
    }
    for (int i = idx - 1; zc > 0; i--) {
      if (i + zc < len) arr[i + zc] = arr[i];
      if (arr[i] == 0) {
        zc--;
        arr[i + zc] = 0;
      }
    }
  }

  public static void main(String[] args) {
    LC1089MoveZeros.duplicateZeros(new int[]{1,0,2,3,0,4,5,0});
  }
}
