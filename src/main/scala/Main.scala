object Main extends App {
  def contextualAbstractions() = {
    DelegateExampleUseCase.use()
    TypeClassExampleUseCase.use()
  }

  contextualAbstractions()
}