/** 拡張メソッドのサンプル */
object ExtensionMethodExampleDefs {
  // Circle型にcircumferenceメソッドを拡張する
  case class Circle(x: Double, y: Double, radius: Double)
  def (c: Circle).circumference: Double = c.radius * math.Pi * 2

  // Seq[String]型にlongestStringsメソッドを拡張する
  trait StringSeqOps {
    def (xs: Seq[String]).longestStrings = {
      val maxLength = xs.map(_.length).max
      xs.filter(_.length == maxLength)
    }
  }

  // 演算子タイプの拡張メソッド
  def (x: String) << (y: String) = s"???? $x $y ????"

  // `extension`を用いた専用構文でも定義可能
  extension stringOps on (xs: Seq[String]) {
    def longestStrings2: Seq[String] = {
      val maxLength = xs.map(_.length).max
      xs.filter(_.length == maxLength)
    }
  }

  // ジェネリクスを用いた`extension`も可能
  extension listOps on [T](xs: List[T]) {
    def second = xs.tail.head
    def third: T = xs.tail.tail.head
  }

  // 無名の`extension`も可能
  extension on [T](xs: List[T])(using Ordering[T]) {
    def largest(n: Int) = xs.sorted.takeRight(n)
  }
}


object ExtensionMethodExample {
  import ExtensionMethodExampleDefs.{_, given}
  def use(): Unit = {
    println("\n--- start ExtensionMethodExample ---")
    val circle = Circle(0, 0, 1)
    println( circle.circumference ) // 6.283185307179586
    println( "abc" << "def" ) // ???? abc def ????

    given ops1 as StringSeqOps
    println( List("here", "is", "a", "list").longestStrings ) // List("here", "list")

    // extension構文で定義してものは`given`をする必要はない
    println( List("here", "is", "a", "list").longestStrings2 ) // List("here", "list")
    println( List(1, 2, 3, 4, 5).third ) // 3
    println( List(1, 2, 5, 12, -3).largest(2) ) // List(5, 12)
  }
}