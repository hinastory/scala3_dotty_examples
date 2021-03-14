package MyExamples

def example1() =
  def parentFunction() =
    val ctx = "sunny"
    println(s"parent func: ctx=${ctx}")
    childFunction(ctx)

  def childFunction(ctx: String) =
    println("child func")
    grandchildFunction(ctx)

  def grandchildFunction(ctx: String) =
    println("grandchild func")
    println(s"It's ${ctx} today.")

  parentFunction()

def example2() =
  case class ContextA(weather: String, year: Int)

  def parentFunction() =
    val ctxA = ContextA("sunny", 2021)
    println(s"parent func: weather=${ctxA.weather}")
    childFunction(ctxA)

  def childFunction(ctxA: ContextA) =
    println(s"child func: ${ctxA.year}")
    grandchildFunction(ctxA)

  def grandchildFunction(ctxA: ContextA) =
    println("grandchild func")
    println(s"It's ${ctxA.weather} today.")
    println(s"This year is ${ctxA.year}")

  parentFunction()

def example3() =
  case class ContextA(weather: String)
  case class ContextB(year: Int)

  def parentFunction() =
    val ctxA = ContextA("sunny")
    println(s"parent func: ${ctxA.weather}")
    childFunction(ctxA)

  def childFunction(ctxA: ContextA) =
    val ctxB = ContextB(2021)
    println(s"child func: ${ctxB.year}")
    grandchildFunction(ctxA, ctxB)

  def grandchildFunction(ctxA: ContextA, ctxB: ContextB) =
    println("grandchild func")
    println(s"It's ${ctxA.weather} today.")
    println(s"This year is ${ctxB.year}")

  parentFunction()

def example4() =
  case class ContextA(weather: String)
  case class ContextB(year: Int)

  def parentFunction() =
    given ctxA: ContextA = ContextA("sunny") //
    println(s"parent func: ${ctxA.weather}")
    childFunction()

  def childFunction()(using ContextA) =
    given ContextB = ContextB(2021)
    println(s"child func: ${summon[ContextB].year}")
    grandchildFunction()

  def grandchildFunction()(using ctxA: ContextA)(using ctxB: ContextB) =
    println("grandchild func")
    println(s"It's ${ctxA.weather} today.")
    println(s"This year is ${ctxB.year}")

  parentFunction()

def example5() =
  case class Person(name: String, age: Int)

  // 型Personに対して拡張メソッドを定義
  extension (p: Person)
    def profile: String = s"Name: ${p.name}, Age: ${p.age}"

  val alexa = Person("Alexa", 12)
  println(alexa.profile)

  // 継承を用いた方法
  class SubPerson(name: String, age: Int) extends Person(name: String, age: Int):
    def profile: String = s"Name: ${name}, Age: ${age}"

  val alexa2 = SubPerson("Alexa", 12)


def example6() =
  case class Person(name: String, age: Int)

  object Company:
    extension (p: Person)
      def profile: String = s"Name: ${p.name}, Age: ${p.age}"
      def hello() =  s"Hello!"

  import Company.*

  val alexa = Person("Alexa", 12)
  println(alexa.profile);
  alexa.hello();

def example7() =
  case class Person(name: String, age: Int)
  case class Japanese(name: String, age: Int, info: String)

  trait CanGreet[A]:
    extension (a: A) def hello(): Unit

  given CanGreet[Person] with
    extension(a: Person) def hello() = println("hello!")

  given CanGreet[Japanese] with
    extension(a: Japanese) def hello() = println(s"こんにちは。実はわたしは、${a.info}なんです。")


  val alexa = Person("Alexa", 12)
  val hanako = Japanese("Hanako", 18, "テニスが趣味")

  alexa.hello()
  hanako.hello()


def example8() =
  // コンテキストを表す型を定義
  case class ContextA(weather: String)
  case class ContextB(year: Int)

  // 親関数
  def parentFunction() =
    given ContextA = ContextA("sunny") // コンテキストの生成(`given`インスタンス)
    println(s"parent func: ${summon[ContextA].weather}")
    childFunction() // コンテキストパラメータの省略（コンテキストの自動引き回し）

  // 子関数
  def childFunction()(using ContextA) =
    given ContextB = ContextB(2021) // コンテキストの生成(`given`インスタンス)
    println(s"child func: ${summon[ContextB].year}")
    grandchildFunction() // コンテキストパラメータの省略(コンテキストの自動引き回し)

  // 孫関数
  def grandchildFunction()(using ContextA)(using ContextB) =
    println("grandchild func")
    println(s"It's ${summon[ContextA].weather} today.")
    println(s"This year is ${summon[ContextB].year}")

  parentFunction() // 親関数の呼び出し


def example9() =
  case class Person(name: String, age: Int)
  case class Japanese(name: String, age: Int, info: String)

  trait CanGreet[A]:
    extension (a: A) def hello(): Unit

  given CanGreet[Person] with
    extension(a: Person) def hello() = println("hello!")

  given CanGreet[Japanese] with
    extension(a: Japanese) def hello() = println(s"こんにちは。実はわたしは、${a.info}なんです。")

  def greeting[T](a: T)(using CanGreet[T]) = a.hello()
  def greeting2[T: CanGreet](a: T) = a.hello()

  val alexa = Person("Alexa", 12)
  val hanako = Japanese("Hanako", 18, "テニスが趣味")

  greeting(alexa)
  greeting(hanako)
