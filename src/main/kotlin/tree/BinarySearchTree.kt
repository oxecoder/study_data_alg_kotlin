package tree

class BinarySearchTree<T : Comparable<T>>() {
  var root: BinaryNode<T>? = null

  fun insert(value: T) {
    root = insert(root, value)
  }

  /**
   * recursive method
   * it requires a base case for terminating recursion
   *   if the current node is null
   */
  private fun insert(node: BinaryNode<T>?, value: T): BinaryNode<T> {
    node ?: return BinaryNode(value)
    if (value < node.value) {
      node.leftChild = insert(node.leftChild, value)
    } else {
      node.rightChild = insert(node.rightChild, value)
    }
    return node
  }

  fun contains(value: T): Boolean {
    root ?: return false

    var found = false
    root?.traverseInOrder {
      if (value == it) {
        found = true
      }
    }
    return found
  }

  override fun toString(): String {
    return root?.toString() ?: "empty tree"
  }
}
