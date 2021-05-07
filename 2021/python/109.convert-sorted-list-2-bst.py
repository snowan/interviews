# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 1. convert linked list to sorted array
    # 2. find mid value -> arr[mid] as root,
    # 3. left node -> [left, mid - 1]
    # 4. right node -> [mid + 1, right]
    # DFS
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        if not head:
            return None
        def convert2Array(head):
            arr = []
            while head != None:
                arr.append(head.val)
                head = head.next
            return arr
        
        def dfs(arr, left, right):
            if left > right:
                return None
            mid = left + (right - left) // 2
            root = TreeNode(arr[mid])
            root.left = dfs(arr, left, mid - 1)
            root.right = dfs(arr, mid + 1, right)
            return root
        
        arr = convert2Array(head)
        return dfs(arr, 0, len(arr) - 1)
        
