package leetcode.tree.LC426;

public class LC426BST2DBl {
  class Node {
    int val;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
  Node prev;
  public Node treeToDoublyList(Node root) {
    if (root == null) return null;
    Node dummy = new Node();
    prev = dummy;
    inorder(root);
    Node newRoot = dummy.right;
    prev.right = newRoot;
    if (newRoot != null) newRoot.left = prev;
    return newRoot;
  }
  private void inorder(Node node) {
    inorder(node.left);
    prev.right = node;
    node.left = prev;
    prev = node;
    inorder(node.right);
  }
}
