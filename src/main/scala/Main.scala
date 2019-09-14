@main def example: Unit =
  // contextual Abstractions
  DelegateExampleUseCase.use()
  TypeClassExampleUseCase.use()

  // Significant Indentation
  IndentBasedExample.use("Monday")
  BraceBasedExample.use("Monday")
