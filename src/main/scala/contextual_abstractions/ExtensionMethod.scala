/** 拡張メソッドのサンプル */
object ExtensionMethodExampleDefs:
  // Circle型にcircumferenceメソッドを拡張する
  case class Circle(x: Double, y: Double, radius: Double)
  extension (c: Circle)
    def circumference: Double = c.radius * math.Pi * 2

  // 演算子の拡張メソッド
  extension (x: String)
    def << (y: String) = s"???? $x $y ????"

  // 中置演算子の拡張メソッド
  extension (x: Int)
    infix def min(y: Int) = if x < y then x else y

  // ジェネリクスを用いた`extension`も可能
  import math.Numeric.Implicits.infixNumericOps
  extension [T](xs: List[T])
    def second = xs.tail.head
    def third: T = xs.tail.tail.head
    def sumBy[U: Numeric](f: T => U) = xs.foldLeft(implicitly[Numeric[U]].zero)((acc, x) => acc + f(x))

object ExtensionMethodExample:
  import ExtensionMethodExampleDefs.{_, given}
  def use(): Unit =
    println("\n--- start ExtensionMethodExample ---")
    val circle = Circle(0, 0, 1)
    println( circle.circumference ) // 6.283185307179586
    println( "abc" << "def" ) // ???? abc def ????

    println( List(1, 2, 3, 4, 5).third ) // 3
    println( List(1, 2, 3, 4, 5).sumBy(identity) ) // 15

end ExtensionMethodExample