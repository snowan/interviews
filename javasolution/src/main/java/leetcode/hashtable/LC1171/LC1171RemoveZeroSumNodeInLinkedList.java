package leetcode.hashtable.LC1171;

import java.util.HashMap;
import java.util.Map;

/**
 * 1171. Remove Zero Sum Consecutive Nodes from Linked List
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 *
 * After doing so, return the head of the final linked list.  You may return any such answer.
 *
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class LC1171RemoveZeroSumNodeInLinkedList {
  /**
   * prefixSum + hashMap
   */
  public ListNode removeZeroSumSublists(ListNode head) {
    ListNode dummy = new ListNode(0);
    ListNode currNode = dummy;
    dummy.next = head;
    int prefixSum = 0;
    Map<Integer, ListNode> prefixSumMap = new HashMap<>();
    while (currNode != null) {
      prefixSum += currNode.val;
      if (prefixSumMap.containsKey(prefixSum)) {
        currNode = prefixSumMap.get(prefixSum).next;
        int sum = prefixSum + currNode.val;
        while (sum != prefixSum) {
          prefixSumMap.remove(sum);
          currNode = currNode.next;
          sum += currNode.val;
        }
        prefixSumMap.get(prefixSum).next = currNode.next;
      } else {
        prefixSumMap.put(prefixSum, currNode);
      }
      currNode = currNode.next;
    }
    return dummy.next;
  }

  class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }
  }
}
