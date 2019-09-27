object OptionalBraceExample
  enum Day
    case Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    def isWeekend: Boolean = this match
      case Saturday | Sunday => true
      case _ => false

  def fromString(str: String): Day =
    try Day.valueOf(str)
      catch
        case _: IllegalArgumentException =>
          throw new IllegalArgumentException(s"$str is not a valid day")
    end try
  end fromString

  def use(dayString: String) =
    val day = fromString(dayString)

    if day.isWeekend then
      println("Today is a weekend")
      println("I will rest")
    else
      println("Today is a workday")
      println("I will work")

    if (day == Day.Wednesday)
      println("Today is a Wednesday")
      println("Bad Day")

    val optNum =
      for
        x <- Option(3)
        y <- Option(2)
      yield
        x + y

    optNum match
    case Some(x) if x > 4 => println("bigger than 4")
    case _ => println("Other")

    // required `-Yindent-colons`
    val z = List(2, 3, 4) map:
      x =>
        val y = x - 1
        y * y

    z.foreach:
      println

object BraceBasedExample {
  enum Day {
    case Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    def isWeekend: Boolean = this match {
      case Saturday | Sunday => true
      case _ => false
    }
  }
  def fromString(str: String): Day = {
    try {
      Day.valueOf(str)
    } catch {
      case _: IllegalArgumentException =>
        throw new IllegalArgumentException(s"$str is not a valid day")
    }
  }

  def use(dayString: String) = {
    val day = fromString(dayString)

    if (day.isWeekend) {
      println("Today is a weekend")
      println("I will rest")
    } else {
      println("Today is a workday")
      println("I will work")
    }

    if (day == Day.Wednesday) {
      println("Today is a Wednesday")
      println("Bad Day")
    }

    val optNum =
      for {
        x <- Option(3)
        y <- Option(2)
      } yield {
        x + y
      }

    optNum match {
    case Some(x) if x > 4 => println("bigger than 4")
    case _ => println("Other")
    }

    val z = List(2, 3, 4) map {
      x => {
        val y = x - 1
        y * y
      }
    }
    z.foreach(println)
  }
}
