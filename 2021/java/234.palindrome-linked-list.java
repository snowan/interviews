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
// solution 1: scan the whole list, and store value into array, then check array is palindome or not

// solution 2: slow and fast, each move, keep track of reverse list. when fast move to end, slow is in the middle, then compare reverse list and slow. 
// found not equal, return false. if until end, return true.
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode reverse = new ListNode(head.val);
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast != null) { // even, do not append last value to reverse
                ListNode temp = new ListNode(slow.val);
                temp.next = reverse;
                reverse = temp;
            }
        }
        // print(reverse);
        // print(slow);
        while (slow != null && reverse != null) {
            if (reverse.val != slow.val) return false;
            reverse = reverse.next;
            slow = slow.next;
        }
        if (slow != null || reverse != null) return false;
        return true;
    }
    private void print(ListNode node) {
        while (node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        System.out.println(node.val);
    }
}

// 1->2->3-2->1
// slow: 1->2->3
// fast: 1->3->1

// 1->2->2->1
// slow: 1->2->2
// fast: 1->2->null
