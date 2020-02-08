# Examples of Dotty (Scala 3)

This repository is for trying out the following Dotty(Scala 3) features:

- Contextual Abstractions
  - [Given Instances](https://dotty.epfl.ch/docs/reference/contextual/delegates.html)
  - [Given Parameters](https://dotty.epfl.ch/docs/reference/contextual/given-clauses.html)
  - [Import Given](https://dotty.epfl.ch/docs/reference/contextual/import-delegate.html)
  - [Extension Methods](https://dotty.epfl.ch/docs/reference/contextual/extension-methods.html)
  - [Implementing Typeclasses](https://dotty.epfl.ch/docs/reference/contextual/typeclasses.html)
- [Optional Braces](https://dotty.epfl.ch/docs/reference/other-new-features/indentation.html)

This repository uses some example code from the following documents:

- [Announcing Dotty 0.13.0-RC1 with Spark support, top level definitions and redesigned implicits](https://dotty.epfl.ch/blog/2019/03/05/13th-dotty-milestone-release.html)
- [Announcing Dotty 0.18.1-RC1 – switch to the 2.13 standard library, indentation-based syntax and other experiments](https://dotty.epfl.ch/blog/2019/08/30/18th-dotty-milestone-release.html)
- [Announcing Dotty 0.19.0-RC1 – further refinements of the syntax and the migration to 2.13.1 standard library](https://dotty.epfl.ch/blog/2019/09/23/19th-dotty-milestone-release.html)
- [Announcing Dotty 0.20.0-RC1 – `with` starting indentation blocks, inline given specializations and more](http://dotty.epfl.ch/blog/2019/11/04/20th-dotty-milestone-release.html)
- [Announcing Dotty 0.22.0-RC1 - syntactic enhancements, type-level arithmetic and more](https://dotty.epfl.ch/blog/2020/02/05/22nd-dotty-milestone-release.html)
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
GivenExample.use()
TypeClassExample.use()
ExtensionMethodExample.use()
OptionalBraceExample.use("Monday")
```
