class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode head = new ListNode(0);
        ListNode node = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode ln : lists) {
            if (ln != null) {
                pq.offer(ln);
            }
        }
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            node.next = curr;
            node = node.next;
            curr = curr.next;
            if (curr != null) pq.offer(curr);
        }
        return head.next;
    }
}
