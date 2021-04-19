class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;
        // move fast to n pos
        for (int i = 0; i <= n; i++) {
            // nth node is the first node, return next
            if (fast == null) return head.next;
            fast = fast.next;
        }
        // move fast and slow together, when fast to the end, slow is moving to previous from nth node from the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // remove nth node
        slow.next = slow.next.next;
        return dummy.next;
    }
}
