package main.leetcode.binarysearch.LC1104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC1104PathZigzagBT {
  public List<Integer> pathInZigZagTree(int label) {
    List<Integer> res = new ArrayList<>();
    int depth = 1;
    while ((int) Math.pow(2, depth) - 1 < label) {
      depth++;
    }
    res.add(label);
    depth--;
    while (label > 1 && depth > 0) {
      label = (int) (Math.pow(2, depth - 1)) + ((int) Math.pow(2, depth) - 1 - label / 2);
      res.add(label);
      depth--;
    }
    Collections.reverse(res);
    return res;
  }
}
