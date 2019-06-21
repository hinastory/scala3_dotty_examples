/** 暗黙のインスタンス、推論可能パラメータのサンプル */
object DelegateExample {
  /** 順序型の定義 */
  trait Ord[T] {
    def compare(x: T, y: T): Int
    def (x: T) < (y: T) = compare(x, y) < 0 // 拡張メソッド記法を使って定義してあります
    def (x: T) > (y: T) = compare(x, y) > 0 // 上記と同様
  }

  /** 順序型のIntの暗黙のインスタンスの定義 */
  delegate IntOrd for Ord[Int] {
    def compare(x: Int, y: Int) =
      if (x < y) -1 else if (x > y) +1 else 0
  }

  /** 順序型のListの暗黙のインスタンスの定義 */
  delegate ListOrd[T] for Ord[List[T]] given (ord: Ord[T]) {
    def compare(xs: List[T], ys: List[T]): Int = (xs, ys) match {
      case (Nil, Nil) => 0
      case (Nil, _) => -1 // 空リストよりも非空リストの方が大きい
      case (_, Nil) => +1 // 同上
      case (x :: xs1, y :: ys1) =>
        val fst = ord.compare(x, y) // 先頭の大きさがLists全体の大きさ
        if (fst != 0) fst else compare(xs1, ys1) // 同じだったら次の要素を再帰的に繰り返す
    }
  }

  /** 推論可能パラメータ */
  def max[T](x: T, y: T) given (ord: Ord[T]): T =
    if (ord.compare(x, y) < 1) y else x

  /** 無名推論可能パラメータ */
  def maximum[T](xs: List[T]) given Ord[T]: T = xs.reduceLeft(max)

  /** コンテキスト境界使った書き換え(Scala2と同様) */
  def maximum2[T: Ord](xs: List[T]): T = xs.reduceLeft(max)

  /** 推論可能パラメータを使って新しい逆順序型クラスインスタンスを作る関数 */
  def descending[T] given (asc: Ord[T]): Ord[T] = new Ord[T] {
    def compare(x: T, y: T) = asc.compare(y, x)
  }

  /** より複雑な推論 */
  def minimum[T](xs: List[T]) given Ord[T] = maximum(xs) given descending
}

/** `DelegateExapmple`の利用方法 */
object DelegateExampleUseCase {
  import DelegateExample._
  import delegate DelegateExample._ // `Ord`の`<`演算子を利用するのに必要

  def use(): Unit = {
    println( max(2,3) ) // 3
    println( max(List(1, 2, 3), Nil) ) // List(1, 2, 3)
    println(List(1, 2, 3) < List(1, 2, 3, 4)) // true
    println(List(9, 2, 3) < List(1, 2, 3, 4)) // false

    val numList = List(1,10,2)
    println( maximum(numList) ) // 10
    println( maximum2(numList) ) // 10
    println( minimum(numList) ) // 1
  }
}
