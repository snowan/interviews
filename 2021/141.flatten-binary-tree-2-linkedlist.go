/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func flatten(root *TreeNode)  {
    if root == nil {
        return
    }
    
//     var curr *TreeNode
//     curr = root
    curr := root
    for curr != nil {
        if curr.Left != nil {
//             var rightmost *TreeNode
//             rightmost = curr.Left
            rightmost := curr.left
            for rightmost.Right != nil {
                rightmost = rightmost.Right
            }
            rightmost.Right = curr.Right
            curr.Right = curr.Left
            curr.Left = nil
        }

        curr = curr.Right
    }  
}
