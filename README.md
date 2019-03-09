# Contextual Abstractionsのサンプル

このプロジェクトは[Scala3に入るかもしれないContextual Abstractionsを味見してみた](https://qiita.com/hinastory/items/6dacb1f61d86f4a5d533)に出てきたコードのサンプルです。このサンプルには以下のドキュメントに載っているサンプルコードの一部が利用されています。

- [Announcing Dotty 0.13.0-RC1 with Spark support, top level definitions and redesigned implicits](https://dotty.epfl.ch/blog/2019/03/05/13th-dotty-milestone-release.html)
- [Dotty Documentation](https://dotty.epfl.ch/docs/)

## 事前準備

以下のインストールが必要です。

- JDK
- Scala
- [sbt](https://www.scala-sbt.org/)

## 利用方法

すぐに実行したい場合は`sbt run`を実行してください。

コンソールから実行したい場合は`sbt console`を実行してください。
コンソールが起動したら以下でサンプルが実行できます。

```scala
ImpliedExampleUseCase.use()
TypeClassExampleUseCase.use()
```
