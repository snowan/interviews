package leetcode.recursion.LC465;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 465. Optimal Account Balancing
 * A group of friends went on holiday and sometimes lent each other money. For example,
 * Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride.
 * We can model each transaction as a tuple (x, y, z) which means person x gave person y $z.
 * Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID),
 * the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 *
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 *
 * Note:
 *
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 *
 * Input:
 * [[0,1,10], [2,0,5]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 *
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 *
 * Output:
 * 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class LC465OptAccountBalance {
  public int minTransfers(int[][] transactions) {
    if (transactions == null || transactions.length == 0) return 0;
    int len = transactions.length;
    if (len == 1) return 1;
    Map<Integer, Integer> balanceMap = new HashMap<>();
    for (int[] tc : transactions) {
      balanceMap.put(tc[0], balanceMap.getOrDefault(tc[0], 0) + tc[2]);
      balanceMap.put(tc[1], balanceMap.getOrDefault(tc[1], 0) - tc[2]);
    }
    List<Integer> debts = balanceMap.values().stream()
        .filter(v -> v != 0)
        .collect(Collectors.toList());
    return helper(0, debts);
  }

  private int helper(int idx, List<Integer> debts) {
    while (idx < debts.size() && debts.get(idx) == 0) idx++;
    if (idx == debts.size()) return 0;
    int counts = Integer.MAX_VALUE;
    for (int i = idx + 1; i < debts.size(); i++) {
      // can be balanced
      if (debts.get(i) * debts.get(idx) < 0) {
        debts.set(i, debts.get(i) + debts.get(idx));
        counts = Math.min(counts, 1 + helper(idx + 1, debts));
        debts.set(i, debts.get(i) - debts.get(idx));
      }
    }
    return counts;
  }
}
