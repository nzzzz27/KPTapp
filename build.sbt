import sbt.Keys._
import sbt._

name := """kpt-app"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice

resolvers ++= Seq(
  "IxiaS Releases" at "http://maven.ixias.net.s3-ap-northeast-1.amazonaws.com/releases"
)

libraryDependencies ++= Seq(
  "net.ixias" %% "ixias"      % "1.1.20",
  "net.ixias" %% "ixias-aws"  % "1.1.20",
  "net.ixias" %% "ixias-play" % "1.1.20",
  "ch.qos.logback" % "logback-classic"      % "1.1.+",

  evolutions,
  // evolutionsのバージョン指定
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",

  // JDBC driverを指定（driverとは、playとmysqlを繋ぐためのアプリ）
  "mysql"             % "mysql-connector-java"  % "8.0.16",
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
