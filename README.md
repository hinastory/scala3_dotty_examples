# Examples of Dotty (Scala 3)

This repository is for trying out the following Dotty(Scala 3) features:

- Contextual Abstractions
- [Significant Indentation](https://dotty.epfl.ch/docs/reference/other-new-features/indentation.html)

This repository uses some example code from the following documents:

- [Announcing Dotty 0.13.0-RC1 with Spark support, top level definitions and redesigned implicits](https://dotty.epfl.ch/blog/2019/03/05/13th-dotty-milestone-release.html)
- [Announcing Dotty 0.18.1-RC1 – switch to the 2.13 standard library, indentation-based syntax and other experiments](https://dotty.epfl.ch/blog/2019/08/30/18th-dotty-milestone-release.html)
- [Dotty Documentation](https://dotty.epfl.ch/docs/)

## Requirements

- JDK
- Scala
- [sbt](https://www.scala-sbt.org/)

## Usage

You can execute:

`sbt run`

Or run sbt console as:

`sbt console`

And then execute:

```scala
ImpliedExampleUseCase.use()
TypeClassExampleUseCase.use()
IndentBasedExample.use("Monday")
```
