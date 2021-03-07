 /** 型クラスのサンプル */
object TypeClassExampleDefs:
  /** 半群の型クラス */
  trait SemiGroup[T]:
    extension (x: T) def combine (y: T): T

  /** モノイドの型クラス */
  trait Monoid[T] extends SemiGroup[T]:
    def unit: T

  /** applyでモノイドを召喚 */
  object Monoid with
    def apply[T](using m: Monoid[T]) = m

  /** `String`モノイド */
  given Monoid[String] with
    extension (x: String) def combine (y: String): String = x.concat(y)
    def unit: String = ""

  /** `Int`モノイド */
  given Monoid[Int] with
    extension (x: Int) def combine (y: Int): Int = x + y
    def unit: Int = 0

  /** モノイドの和を求める */
  def sum[T: Monoid](xs: List[T]): T =
    xs.foldLeft(Monoid[T].unit)(_ combine _)

  /** 関手の型クラス */
  trait Functor[F[_]]:
    extension [A](x: F[A])
      def map[B](f: A => B): F[B]

  /** モナドの型クラス */
  trait Monad[F[_]] extends Functor[F]:
    extension [A](x: F[A])
      def flatMap[B](f: A => F[B]): F[B]
      def map[B](f: A => B) = x.flatMap(f `andThen` pure)

    def pure[A](x: A): F[A]

  /** リストモナドのインスタンスを定義 */
  given listMonad: Monad[List] with
    extension [A](xs: List[A])
      def flatMap[B](f: A => List[B]): List[B] = xs.flatMap(f)

    def pure[A](x: A): List[A] = List(x)

  /** リーダモナドのインスタンスを定義 */
  given readerMonad[Ctx]: Monad[[X] =>> Ctx => X] with
    extension [A](x: Ctx => A)
      def flatMap[B](f: A => Ctx => B): Ctx => B = ctx => f(x(ctx))(ctx)
    def pure[A](x: A): Ctx => A = ctx => x

  /** 関手の利用 */
  def transform[F[_], A, B](src: F[A], func: A => B)(using Functor[F]): F[B] = src.map(func)

  /** コンテキスト境界を使った書き換え */
  def transform2[F[_]: Functor, A, B](src: F[A], func: A => B): F[B] = src.map(func)
end TypeClassExampleDefs

/** `TypeClassExampleDefs`の利用方法 */
object TypeClassExample:
  import TypeClassExampleDefs.{given, _}

  def use(): Unit =
    println("\n--- start TypeClassExample ---")
    println( sum(List("abc", "def", "gh")) ) // abcdefgh
    println( sum(List(1, 2, 3)) ) // 6
    println( summon[Monad[List]].pure(12) ) // List(12)

    println( transform(List(1, 2, 3), (_:Int) * 2) ) // List(2, 4, 6)

    // Reader Monad Example
    val calc: Int => Int = for {
      x <- (e:Int) => e + 1
      y <- (e:Int) => e * 10
    } yield x + y

    println( calc(3) ) // 34

end TypeClassExample
