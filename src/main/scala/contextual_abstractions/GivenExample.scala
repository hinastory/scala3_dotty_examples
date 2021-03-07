/** `given`インスタンス、`using`節のサンプル */
object GivenExampleDefs:
  /** 順序型の定義 */
  trait Ord[T]:
    def compare(x: T, y: T): Int
    extension (x: T) def < (y: T) = compare(x, y) < 0 // 拡張メソッド記法を使って定義してあります
    extension (x: T) def > (y: T) = compare(x, y) > 0 // 上記と同様

  /** 順序型のIntの`given`インスタンスの定義 */
  given intOrd: Ord[Int] with
    def compare(x: Int, y: Int) =
      if (x < y) -1 else if (x > y) +1 else 0

  /** 順序型のListの`given`インスタンスの定義 */
  given listOrd[T](using ord: Ord[T]): Ord[List[T]]  with
    def compare(xs: List[T], ys: List[T]): Int = (xs, ys) match
      case (Nil, Nil) => 0
      case (Nil, _) => -1 // 空リストよりも非空リストの方が大きい
      case (_, Nil) => +1 // 同上
      case (x :: xs1, y :: ys1) =>
        val fst = ord.compare(x, y) // 先頭の大きさがLists全体の大きさ
        if (fst != 0) fst else compare(xs1, ys1) // 同じだったら次の要素を再帰的に繰り返す

  /** `using`節 */
  def max[T](x: T, y: T)(using ord: Ord[T]): T =
    if (ord.compare(x, y) < 1) y else x

  /** 無名`using`節 */
  def maximum[T](xs: List[T])(using Ord[T]): T = xs.reduceLeft(max)

  /** コンテキスト境界使った書き換え(Scala2と同様) */
  def maximum2[T: Ord](xs: List[T]): T = xs.reduceLeft(max)

  /** `using`節を使って新しい逆順序型クラスインスタンスを作る関数 */
  def descending[T](using asc: Ord[T]): Ord[T] = new Ord[T]:
    def compare(x: T, y: T) = asc.compare(y, x)

  /** より複雑な推論 */
  def minimum[T](xs: List[T])(using Ord[T]) = maximum(xs)(using descending)
end GivenExampleDefs


/** `GivenExample`の利用方法 */
object GivenExample:
  import GivenExampleDefs.{_, given} // givenは　`Ord`の`<`演算子を利用するのに必要

  def use(): Unit =
    println("\n--- start GivenExample ---")

    println( max(2,3) ) // 3
    println( max(List(1, 2, 3), Nil) ) // List(1, 2, 3)
    println(List(1, 2, 3) < List(1, 2, 3, 4)) // true
    println(List(9, 2, 3) < List(1, 2, 3, 4)) // false

    val numList = List(1,10,2)
    println( maximum(numList) ) // 10
    println( maximum2(numList) ) // 10
    println( minimum(numList) ) // 1

end GivenExample
