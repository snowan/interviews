# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def plusOne(self, head: ListNode) -> ListNode:
        def helper(head):
            if head is None:
                return 1
            carry = helper(head.next)
            currSum = carry + head.val
            head.val = currSum % 10
            return currSum // 10
        if head is None:
            return head
        carry = helper(head)
        if carry == 1:
            res = ListNode(1)
            res.next = head
            return res
        return head
        