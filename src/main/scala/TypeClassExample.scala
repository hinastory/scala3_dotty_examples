/** 型クラスのサンプル */
object TypeClassExample {
  /** 関手の型クラス */
  trait Functor[F[_]] {
    def (x: F[A]) map [A, B] (f: A => B): F[B]
  }

  /** モナドの型クラス */
  trait Monad[F[_]] extends Functor[F] {
    def (x: F[A]) flatMap [A, B] (f: A => F[B]): F[B]
    def (x: F[A]) map [A, B] (f: A => B) = x.flatMap(f `andThen` pure)

    def pure[A](x: A): F[A]
  }

  /** リストモナドのインスタンスを定義 */
  delegate ListMonad for Monad[List] {
    def (xs: List[A]) flatMap [A, B] (f: A => List[B]): List[B] =
      xs.flatMap(f)
    def pure[A](x: A): List[A] =
      List(x)
  }

  /** リーダモナドのインスタンスを定義 */
  delegate ReaderMonad[Ctx] for Monad[[X] =>> Ctx => X] {
    def (r: Ctx => A) flatMap [A, B] (f: A => Ctx => B): Ctx => B =
      ctx => f(r(ctx))(ctx)
    def pure[A](x: A): Ctx => A =
      ctx => x
  }

  /** 関手の利用 */
  def transform[F[_], A, B](src: F[A], func: A => B) given Functor[F]: F[B] = src.map(func)

  /** コンテキスト境界を使った書き換え */
  def transform2[F[_]: Functor, A, B](src: F[A], func: A => B): F[B] = src.map(func)
}

/** `TypeClassExample`の利用方法 */
object TypeClassExampleUseCase {
  import TypeClassExample._
  import delegate TypeClassExample._

  def use(): Unit = {
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
