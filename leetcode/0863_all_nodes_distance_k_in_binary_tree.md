## Problem: 863. All Nodes Distance K in Binary Tree

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/``

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:

Input: root = [1], target = 1, k = 3
Output: []
 

 Constraints:

 The number of nodes in the tree is in the range [1, 500].
 0 <= Node.val <= 500
 All the values Node.val are unique.
 target is the value of one of the nodes in the tree.
 0 <= k <= 1000


## Solutions
### DFS 
in order to get distance K from target node, need to traverse from left node, right node, and parent node. with given node, already have left and right nodes, need to build a map of
current node with parent node. 

then DFS to traverse to distance K from target node to get all nodes 

to reduce repeat visit, using `set` to keep track of visited nodes


```

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        # build node<>parent map
        def buildParentMap(node, parent, parentMap):
            if not node:
                return
            parentMap[node] = parent
            buildParentMap(node.left, node, parentMap)
            buildParentMap(node.right, node, parentMap)

        # DFS traverse starting from node
        def dfs(node, distances):
            if not node or node in visited:
                return

            visited.add(node)
            if distances == k:
                res.append(node.val)
            elif distances < k:
                dfs(node.left, distances + 1)
                dfs(node.right, distances + 1)
                dfs(parentMap[node], distances + 1)

        parentMap = {}
        buildParentMap(root, None, parentMap)

        visited = set()
        res = []
        dfs(target, 0)

        return res

```


### BFS 

build a graph with all nodes connected, {node: [left, right, parent]}

BFS to traverse starting from target node, found all nodes with distances k 

using visited `set()` to reduce duplicate visit

```python``
class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        # build graph
        def buildGraph(node, parent, graph):
            if not node:
                return
            if parenet:
                graph[node].append(parent)
            if node.left:
                graph[node].append(node.left)
                buildGraph(node.left, node, graph) # keep traverse left tree
            if node.right:
                graph[node].append(node.right)
                buildGraph(node.right, node, graph) # keep traverse right tree

        graph = collections.defaultdict(list)
        buildGraph(root, None, graph)

        # BFS 
        visited = set()
        res = []
        queue = collections.deque([target, 0])

        while queue:
            node, distance = queue.popleft()
            if node in visited:
                continue
            visited.add(node)
            if distance == k:
                res.append(node.val)
            elif distance < k:
                for child in graph[node]:
                    queue.append([child, distance + 1])

        return res

```

