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
    var current = root
    collection.forEach {
      val child = current.children[it] ?: return
      current = child
    }
    if (!current.isTerminating) return
    current.isTerminating = false

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

