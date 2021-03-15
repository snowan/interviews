/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode begPrev = dummy;
        ListNode endPrev = dummy;
        for (int i = 0; i < k - 1; i++) {
            begPrev = begPrev.next;
        }
        ListNode next = begPrev.next.next;
        while (next != null) {
            endPrev = endPrev.next;
            next = next.next;
        }
        // System.out.println("beg= " + begPrev.val + ", end= " + endPrev.val);
        ListNode beg = begPrev.next;
        ListNode end = endPrev.next;
        ListNode begAfter = beg.next;
        ListNode endAfter = end.next;
        if (end.next == beg) {
            endPrev.next = beg;
            beg.next = end;
            end.next = begAfter;
        } else if (endPrev == beg) {
            begPrev.next = end;
            end.next = beg;
            beg.next = endAfter;
        } else {
            begPrev.next = end;
            end.next = begAfter;
            endPrev.next = beg;
            beg.next = endAfter;
        }
        
        return dummy.next;
    }
  
}
