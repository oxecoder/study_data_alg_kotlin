package tree

/**
 * AVL node is a self balancing tree named after its inventors Adelson-Velsky and Landis
 *
 * BST and AVL trees shares much of the same implementation. The difference is a balancing component
 */
class AVLNode<T : Comparable<T>>() : BinarySearchTree<T>() {
  /**
   * height of a node is the longest distance from the current node to a leaf node
   */
  var height = 0

}