package tree

/**
 * trie is a tree that specializes in storing data that can be represented as a collection such as English words
 * an usecase of trie can be used to storing words that need to be looked up such as google showing suggestions based on what you type in the search bar
 *
 * @param key: data for node (root does not have one)
 * @param parent: reference to parent. simplifies remove operation
 */
class TrieNode<Key>(var key: Key?, var parent: TrieNode<Key>?) {
  // a trie node may have multiple children
  val children: HashMap<Key, TrieNode<Key>> = HashMap()

  /**
   * used to indicate the termination of a collection/word
   * may not necessarily be a leaf node
   */
  var isTerminating = false

  override fun toString(): String {
    return "key: ${key?.toString()}"
  }


}