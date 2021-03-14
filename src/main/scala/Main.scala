@main def example: Unit =
  // Contextual Abstractions
  GivenExample.use()
  TypeClassExample.use()
  ExtensionMethodExample.use()

  // Optional Brace
  OptionalBraceExample.use("Monday")
  BraceBasedExample.use("Monday")