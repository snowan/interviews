/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func levelOrder(root *TreeNode) [][]int {
    res := [][]int{}
    dfs(root, 0, &res)
    return res
}
func dfs(node *TreeNode, depth int, res *[][]int) {
    if node == nil {
        return
    }
    if len(*res) <= depth {
        *res = append(*res, []int{})
    }
    (*res)[depth] = append((*res)[depth], node.Val)
    dfs(node.Left, depth + 1, res)
    dfs(node.Right, depth + 1, res)
}
