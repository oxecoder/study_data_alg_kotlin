package tree

open class BinarySearchTree<T : Comparable<T>>() {
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
    var current = root

    while (current != null) {
      if (current.value == value) {
        return true
      }

      // since binary search tree always has lower value on left child
      current = if (value < current.value) {
        current.leftChild
      } else {
        current.rightChild
      }
    }

    return false
  }

  fun remove(value: T) {
    root = remove(root, value)
  }

  private fun remove(node: BinaryNode<T>?, value: T): BinaryNode<T>? {
    /**
     * terminate when node is null
     */
    node ?: return null
    when {
      value == node.value -> {
        if (node.leftChild == null && node.rightChild == null) {
          return null
        }

        if (node.leftChild == null) {
          return node.rightChild
        }

        if (node.rightChild == null) {
          return node.leftChild
        }

        node.rightChild?.min?.value?.let { node.value = it }
        node.rightChild = remove(node.rightChild, node.value)
      }

      value < node.value -> node.leftChild = remove(node.leftChild, value)
      else -> node.rightChild = remove(node.rightChild, value)
    }
    return node
  }

  override fun toString(): String {
    return root?.toString() ?: "empty tree"
  }
}
