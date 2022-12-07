import tree.*

fun main(args: Array<String>) {
//  println("Hello World!")
//  // Try adding program arguments via Run/Debug configuration.
//  // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//  println("Program arguments: ${args.joinToString()}")

  val bst = AVLSearchTree<Int>()

  (0..5).forEach { bst.insert(it) }
  println(bst)

  if (bst.contains(5)) {
    println("Found 5")
  } else {
    println("Could not find 5")
  }
}

