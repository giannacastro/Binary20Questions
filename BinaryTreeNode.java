// represents a node in the binary tree
class BinaryTreeNode {

  //data that is stored in the node
  String data;
  // refers to the left for the current node
  BinaryTreeNode left;

  //refers to the right for the current node
  BinaryTreeNode right;

  BinaryTreeNode(String data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}