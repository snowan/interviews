package leetcode.linkedlist.LC25;

public class LC25ReverseNodesInKGroups {
  class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode start = dummy;
    ListNode end = head;
    int count = 0;
    while (end != null) {
      count++;
      // group
      if (count % k == 0) {
        // reverse linked list (start, end]
        start = reverse(start, end.next);
        end = start.next;
      } else {
        end = end.next;
      }
    }
    return dummy.next;
  }

  // reverse linked list from range (start, end], return last node.
  private ListNode reverse(ListNode pre, ListNode next) {
    ListNode last = pre.next;
    ListNode curr = last.next;

    while (curr != next) {
      last.next = curr.next;
      curr.next = pre.next;
      pre.next = curr;
      curr = last.next;
    }
    return last;
  }
}
