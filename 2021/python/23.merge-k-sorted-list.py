"""
23. Merge k Sorted Lists
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
"""
"""
Solution:
priority queue to keep ListNode, sort by value.
poll ListNode one by one, put next listnode into queue until queue is empty
"""
def mergeKLists(lists: List[ListNode]) -> ListNode:
    # ListNode heapq customize comprator
    ListNode.__lt__ = lambda a, b: a.val < b.val
    head = node = ListNode(0)
    pq = []
    for ln : lists:
        if ln:
            heapq.heappush(pq, ln)
    while pq:
        curr = heapq.heappop(pq)
        node.next = curr
        node = node.next
        curr = curr.next
        if curr:
            heapq.heappush(pq, curr)

    return head.next

