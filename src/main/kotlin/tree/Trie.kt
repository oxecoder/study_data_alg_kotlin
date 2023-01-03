package tree

class Trie<Key> {
  private val root = TrieNode<Key>(key = null, parent = null)

  /**
   * @param list collection to store. for instance a word like apple
   *
   * complexity is O(k) since traversal of the whole list is needed
   * k: size of list
   */
  fun insert(list: List<Key>) {
    // current keeps track of traversal progress
    var current = root

    // look the collection and insert to trie
    list.forEach { element ->
      if (current.children[element] == null) {
        current.children[element] = TrieNode(element, current)
      }
      current = current.children[element]!!
    }

    // terminate collection at end
    current.isTerminating = true
  }

  fun contains(list: List<Key>): Boolean {
    var current = root
    list.forEach { element ->
      val child = current.children[element] ?: return false
      current = child
    }

    return current.isTerminating
  }

  fun remove(collection: List<Key>) {
    // check if collection is part of the trie
    var current = root
    collection.forEach {
      val child = current.children[it] ?: return
      current = child
    }
    if (!current.isTerminating) return

    // set isTerminating to false so that the collection can be removed from the trie
    // ex. if cut and cute are in the trie, t and e will be terminating nodes. removing t as a terminating node will remove cut from the trie
    current.isTerminating = false

    // if there are no other children in the current node it will indicate that other collections do not depend on the current node
    // thus, clear children
    val parent = current.parent
    while (current.children.isEmpty() && !current.isTerminating) {
      parent?.let {
        current.children.clear()
        current = it
      }
    }
  }
}

fun Trie<Char>.contains(string: String): Boolean {
  return contains(string.toList())
}

fun Trie<Char>.insert(string: String) {
  insert(string.toList())
}

fun Trie<Char>.remove(string: String) {
  remove(string.toList())
}

