package tree

typealias Visitor<T> = ((T) -> Unit)

class BinaryNode<T>(val value: T) {
  var leftChild: BinaryNode<T>? = null
  var rightChild: BinaryNode<T>? = null

  /**
   * travels left child -> parent -> right child
   *
   * If the current node has a left child, recursively visit left child first
   * Then visit the node
   * If the current node has a right child recursively visit right child
   */
  fun traverseInOrder(visit: ((T) -> Unit)) {
    leftChild?.traverseInOrder(visit)
    visit(value)
    rightChild?.traverseInOrder(visit)
  }

  /**
   * travels current node > recursively left child > right child
   *
   * visit the current node
   * Recursively visit the left and right child
   */
  fun traversePreOrder(visit: Visitor<T>) {
    visit(value)
    leftChild?.traversePreOrder(visit)
    rightChild?.traversePreOrder(visit)
  }

  /**
   * travels left child recursive > visit right child > visit parent (root)
   *
   * visit lefter most child recursively
   * visit right child
   * visit root
   */
  fun traversePostOrder(visit: Visitor<T>) {
    leftChild?.traversePostOrder(visit)
    rightChild?.traversePostOrder(visit)
    visit(value)
  }

  private fun diagram(
    node: BinaryNode<T>?,
    top: String = "",
    root: String = "",
    bottom: String = "",
  ): String {
    return node?.let {
      if (node.leftChild == null && node.rightChild == null) {
        "$root${node.value}\n"
      } else {
        diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
          root + "${node.value}\n" + diagram(
          node.leftChild,
          "$bottom│ ", "$bottom└──", "$bottom "
        )
      }
    } ?: "${root}null\n"
  }

  override fun toString() = diagram(this)
}
