import tree.Trie
import tree.contains
import tree.insert
import tree.remove

fun main(args: Array<String>) {
  `given trie when remove child then remove only that child`()
}

private fun `given trie when insert word then show contains`() {
  val word = "cute"
  val trie = Trie<Char>()
  trie.insert(word.toList())
  if (trie.contains(word.toList())) {
    println("$word is in the tire")
  }
}

private fun `given trie when remove child then remove only that child`() {
  val trie = Trie<Char>()

  trie.insert("cut")
  trie.insert("cute")

  println("\n*** Before removing ***")
  assert(trie.contains("cut"))
  println("\"cute\" is in the trie")

  println("\n*** After removing cut ***")
  trie.remove("cut")
  assert(!trie.contains("cut"))
  assert(trie.contains("cute"))
  println("\"cute\" is still in the trie")
}

