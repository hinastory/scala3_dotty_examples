val dottyVersion = "0.20.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-examples",
    version := "0.3.0",

    scalaVersion := dottyVersion,

    scalacOptions ++= Seq("-Yindent-colons"),

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
