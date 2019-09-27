/** 型クラスのサンプル */
object TypeClassExampleDefs {
  /** 半群の型クラス */
  trait SemiGroup[T] {
    def (x: T) combine (y: T): T
  }

  /**モノイドの型クラス */
  trait Monoid[T] extends SemiGroup[T] {
    def unit: T
  }

  /** applyでモノイドを召喚
   * `summon`は`implicitly`相当
   */
  object Monoid {
    def apply[T](given Monoid[T]) = summon[Monoid[T]]
  }

  /** 文字列のモノイド */
  given Monoid[String] {
    def (x: String) combine (y: String): String = x.concat(y)
    def unit: String = ""
  }

  /** 数値のモノイド */
  given Monoid[Int] {
    def (x: Int) combine (y: Int): Int = x + y
    def unit: Int = 0
  }

  /** モノイドの和を求める */
  def sum[T: Monoid](xs: List[T]): T =
    xs.foldLeft(Monoid[T].unit)(_.combine(_))


  /** 関手の型クラス */
  trait Functor[F[_]] {
    def (x: F[A]) map [A, B] (f: A => B): F[B]
  }

  /** モナドの型クラス */
  trait Monad[F[_]] extends Functor[F] {
    def (x: F[A])flatMap [A, B] (f: A => F[B]): F[B]
    def (x: F[A])map [A, B] (f: A => B) = x.flatMap(f `andThen` pure)

    def pure[A](x: A): F[A]
  }

  /** リストモナドのインスタンスを定義 */
  given listMonad: Monad[List] {
    def (xs: List[A]) flatMap [A, B] (f: A => List[B]): List[B] =
      xs.flatMap(f)
    def pure[A](x: A): List[A] =
      List(x)
  }

  /** リーダモナドのインスタンスを定義 */
  given readerMonad[Ctx]: Monad[[X] =>> Ctx => X] {
    def (r: Ctx => A) flatMap [A, B] (f: A => Ctx => B): Ctx => B =
      ctx => f(r(ctx))(ctx)
    def pure[A](x: A): Ctx => A =
      ctx => x
  }

  /** 関手の利用 */
  def transform[F[_], A, B](src: F[A], func: A => B)(given Functor[F]): F[B] = src.map(func)

  /** コンテキスト境界を使った書き換え */
  def transform2[F[_]: Functor, A, B](src: F[A], func: A => B): F[B] = src.map(func)
}

/** `TypeClassExampleDefs`の利用方法 */
object TypeClassExample {
  import TypeClassExampleDefs.{given, _}

  def use(): Unit = {
    println( sum(List("abc", "def", "gh")) ) // 文字列の和
    println( sum(List(1, 2, 3)) ) // 数値の和

    println( transform(List(1, 2, 3), (_:Int) * 2) ) // List(2, 4, 6)

    /* リーダーモナドの例はずだが・・・
    以下の例は0.13.0-RC1ではコンパイルが終わらない・・・
    0.16.0-RC3でも同様に終わらない
    O.18.1-RC1で動いた!
    */
    val calc: Int => Int = for {
      x <- (e:Int) => e + 1
      y <- (e:Int) => e * 10
    } yield x + y

    println( calc(3) ) // 34
  }
}
