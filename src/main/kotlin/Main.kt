import tree.Trie

fun main(args: Array<String>) {
  `given trie when insert word then show contains`()
  println("hello world")
}

private fun `given trie when insert word then show contains`() {
  val word = "cute"
  val trie = Trie<Char>()
  trie.insert(word.toList())
  if (trie.contains(word.toList())) {
    println("$word is in the tire")
  }
}

