package leetcode.linkedlist.LC369;

import java.util.Stack;

/**
 * 369. Plus One Linked List
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example :
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 */
public class LC369PlusOneLinkedList {
  class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }
  }

  public ListNode plusOne(ListNode head) {
    if (head == null) return head;
    int carry = helper(head);
    if (carry == 1) {
      ListNode res = new ListNode(1);
      res.next = head;
      return res;
    }
    return head;
  }

  private int helper(ListNode head) {
    if (head == null) return 1;
    int carry = helper(head.next);
    int sum = head.val + carry;
    head.val = sum % 10;
    return sum / 10;
  }

  /**
   * Solution #2. Reverse linked list using Stack.
   */
  public ListNode plusOne_statck(ListNode head) {
    if (head == null) return head;
    Stack<ListNode> stack = new Stack<>();
    while (head != null) {
      stack.push(head);
      head = head.next;
    }
    int carry = 1;
    while (!stack.isEmpty()) {
      ListNode curr = stack.pop();
      int sum = curr.val + carry;
      curr.val = sum % 10;
      carry = sum / 10;

      ListNode prev = head;
      curr.next = prev;
      head = curr;
    }
    if (carry == 1) {
      ListNode res = new ListNode(1);
      res.next = head;
      return res;
    }
    return head;
  }

  /**
   * Solution #3. reverse linkedlist (using stack)
   * from last node, check current node value, if it is 9, then set current value to 0, otherwise, current value +1,
   * return head.
   *
   * for example:
   * 1. 1->2->3->4, 4 < 9, then 4+1=5, return head 1->2->3->5
   * 2. 1->2->3->9, 9 == 9, then set 0, 3 < 9, 3+1=4, return head, 1->2->4->0
   * 3. 9->9->9, all 9, set to 0, add new listnode(1) to the head. 1->0->0->0
   */
  public ListNode plusOne3(ListNode head) {
    if (head == null) return head;
    Stack<ListNode> stack = new Stack<>();
    ListNode curr = head;
    while (curr != null) {
      stack.push(curr);
      curr = curr.next;
    }
    while (!stack.isEmpty()) {
      ListNode temp = stack.pop();
      if (temp.val < 9) {
        temp.val++;
        return head;
      } else {
        temp.val = 0;
      }
    }
    ListNode res = new ListNode(1);
    res.next = head;
    return res;
  }
}
