# Examples of Scala 3(Dotty)

This repository is for trying out the following Scala 3(Dotty) features:

- Indent-based Syntax
    - [New Control Syntax](https://dotty.epfl.ch/docs/reference/other-new-features/control-syntax.html)
    - [Optional Braces](https://dotty.epfl.ch/docs/reference/other-new-features/indentation.html)
- Contextual Abstractions
  - [Given Instances](https://dotty.epfl.ch/docs/reference/contextual/givens.html)
  - [Using Clauses](https://dotty.epfl.ch/docs/reference/contextual/using-clauses.html)
  - [Importing Givens](https://dotty.epfl.ch/docs/reference/contextual/given-imports.html)
  - [Extension Methods](https://dotty.epfl.ch/docs/reference/contextual/extension-methods.html)
  - [Implementing Typeclasses](https://dotty.epfl.ch/docs/reference/contextual/type-classes.html)

This repository uses some example code from the following documents:

- [Dotty Documentation](https://dotty.epfl.ch/docs/)

 The examples in this repository support `3.0.0-RC1` syntax.
## Requirements

- JDK
- [sbt](https://www.scala-sbt.org/)
    - 1.1.4+
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
