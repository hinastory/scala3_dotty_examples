@main def example: Unit =
  // Contextual Abstractions
  GivenExample.use()
  TypeClassExample.use()

  // Optional Brace
  OptionalBraceExample.use("Monday")
  BraceBasedExample.use("Monday")
