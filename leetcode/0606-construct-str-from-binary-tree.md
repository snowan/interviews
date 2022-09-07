## Problem: 606 Construct String from Binary Tree

https://leetcode.com/problems/construct-string-from-binary-tree/

Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder traversal way, and return it.

Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and the original binary tree.

 

 Example 1:
 Input: root = [1,2,3,4]
 Output: "1(2(4))(3)"
 Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)"

 Example 2:

 Input: root = [1,2,3,null,4]
 Output: "1(2()(4))(3)"
 Explanation: Almost the same as the first example, except we cannot omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
  

  Constraints:

  The number of nodes in the tree is in the range [1, 104].
  -1000 <= Node.val <= 1000


## Solution

Recursive traverse binray tree, 
1. if tree is empty, return empty str ''
2. for each child, record '( + (string of child) + )'
3. for tree only has right child, record left child as '()' instead of empty string ''

```python
class Solution:
    def tree2str(self, root: Optional[TreeNode]) -> str:
        if not root:
            return ''
        
        left = '({})'.format(self.tree2str(root.left)) if root.left or root.right else ''

        right = '({})'.format(self.tree2str(root.right)) if root.right else ''

        return '{}{}{}'.format(root.val, left, right)

```
