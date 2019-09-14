@main def example: Unit =
  // contextual Abstractions
  DelegateExampleUseCase.use()
  TypeClassExampleUseCase.use()

  // Significant Indentation
  IndentBaseExample.use("Monday")
