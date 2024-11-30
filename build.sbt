name := "FirstScalaApp"

version := "1.0"

scalaVersion := "3.1.3" // Или ваша версия Scala

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "4.0.7",
  "org.scalatest" %% "scalatest" % "3.2.19" % "test" // Зависимость для тестирования (опционально)
)
// Настройки для скалы (можно настроить под свои нужды)
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
)
