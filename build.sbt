val scala3Version = "3.0.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-scala3-examples",
    version := "0.5.0",

    scalaVersion := scala3Version,

    scalacOptions ++= Seq("-Yindent-colons"),

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
