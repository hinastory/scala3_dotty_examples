package MyFunctionsB

import MyContextDefs.*

// 孫関数
def grandchildFunction()(using ctxA: ContextA)(using ctxB: ContextB) =
  println("grandchild func")
  println(s"It's ${ctxA.weather} today.")
  println(s"This year is ${ctxB.year}")
