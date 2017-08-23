
name := """UserDataCollector"""

version := "2.0-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala, PlayJava, QueryDSLPlugin).disablePlugins(PlayFilters)

queryDSLPackage := "models"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.2.10.Final",
  "org.postgresql" % "postgresql" % "42.1.4",
  "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final",
  "com.mysema.querydsl" % "querydsl-apt" % "3.6.2",
  "com.mysema.querydsl" % "querydsl-jpa" % "3.3.4",
  "javax.websocket" % "javax.websocket-api" % "1.1" % "provided",
  ws
)
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))