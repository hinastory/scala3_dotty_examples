package MyFunctionsA

import MyContextDefs.*
import MyFunctionsB.*

// 親関数
def parentFunction() =
  import MyContextValues.given
  println(s"parent func: ${ctxA.weather}")
  childFunction() // コンテキストパラメータの省略（コンテキストの自動引き回し）

// 子関数
def childFunction()(using ctxA: ContextA) =
  import MyContextValues.given
  println(s"child func: ${ctxB.year}")
  grandchildFunction() // コンテキストパラメータの省略(コンテキストの自動引き回し)
