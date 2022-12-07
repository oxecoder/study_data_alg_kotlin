package tree

/**
 * AVL node is a self-balancing tree named after its inventors Adelson-Velsky and Landis
 *
 * BST and AVL trees shares much of the same implementation. The difference is a balancing component
 */
class AVLNode<T : Comparable<T>>(var value: T) {
  var height = 0

  val leftHeight: Int
    get() = leftChild?.height ?: -1
  val rightHeight: Int
    get() = rightChild?.height ?: -1


  /**
   * in order to be a balanced tree the height of the left and right children of each node must differ at most by 1 (in absolute terms)
   */
  val balanceFactor: Int
    get() = leftHeight - rightHeight

  var leftChild: AVLNode<T>? = null
  var rightChild: AVLNode<T>? = null

  /**
   * recursively search for the minimum node in a subtree
   */
  val min: AVLNode<T>
    get() = leftChild?.min ?: this

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
    node: AVLNode<T>?,
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