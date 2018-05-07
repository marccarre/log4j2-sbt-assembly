import Dependencies._

val log4j2Version = "2.11.0"
val jacksonVersion = "2.9.5"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "log4j2-sbt-assembly",
    libraryDependencies ++= Seq(
      // Logging via log4j2:
      "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0",
      "org.apache.logging.log4j" % "log4j-api" % log4j2Version,
      "org.apache.logging.log4j" % "log4j-core" % log4j2Version % Runtime,
      // Structured logging, in JSON via log4j2:
      "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion % Runtime,
      "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion % Runtime,
      "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion % Runtime,
      // Testing:
      scalaTest % Test
    )
  )
