package tree

import kotlin.math.max

open class AVLSearchTree<T : Comparable<T>>() {
  var root: AVLNode<T>? = null

  fun insert(value: T) {
    root = insert(root, value)
  }

  /**
   * recursive method
   * it requires a base case for terminating recursion
   *   if the current node is null
   */
  private fun insert(node: AVLNode<T>?, value: T): AVLNode<T> {
    node ?: return AVLNode(value)
    if (value < node.value) {
      node.leftChild = insert(node.leftChild, value)
    } else {
      node.rightChild = insert(node.rightChild, value)
    }

    // check if balancing is needed
    val balancedNode = balanced(node)
    balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
    return balancedNode
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

  private fun remove(node: AVLNode<T>?, value: T): AVLNode<T>? {
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

    val balancedNode = balanced(node)
    balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
    return balancedNode
  }

  private fun leftRotate(node: AVLNode<T>): AVLNode<T> {
    val pivot = node.rightChild!!
    node.rightChild = pivot.leftChild
    pivot.leftChild = node
    node.height = max(node.leftHeight, node.rightHeight) + 1
    pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
    return pivot
  }

  private fun rightRotate(node: AVLNode<T>): AVLNode<T> {
    val pivot = node.leftChild!!
    node.leftChild = pivot.rightChild
    pivot.rightChild = node
    node.height = max(node.leftHeight, node.rightHeight) + 1
    pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
    return pivot
  }

  private fun rightLeftRotate(node: AVLNode<T>): AVLNode<T> {
    val rightChild = node.rightChild ?: return node
    node.rightChild = rightRotate(rightChild)
    return leftRotate(node)
  }

  private fun leftRightRotate(node: AVLNode<T>): AVLNode<T> {
    val leftChild = node.leftChild ?: return node
    node.leftChild = leftRotate(leftChild)
    return rightRotate(node)
  }

  /**
   * balanceFactor of 2 suggests that the left child is heavier
   * balanceFactor of -2 suggests that the right child is heavier
   * else balanced
   *
   * by using the sign of the balanceFactor of child, determine if single or double rotation is required
   */
  private fun balanced(node: AVLNode<T>): AVLNode<T> {
    return when (node.balanceFactor) {
      2 -> {
        if (node.leftChild?.balanceFactor == -1) {
          leftRightRotate(node)
        } else {
          rightRotate(node)
        }
      }

      (-2) -> {
        if (node.rightChild?.balanceFactor == 1) {
          rightLeftRotate(node)
        } else {
          leftRotate(node)
        }
      }

      else -> node
    }

  }

  override fun toString(): String {
    return root?.toString() ?: "empty tree"
  }
}
