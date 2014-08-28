package com.primotus.persistence

import akka.actor.ActorSystem
import scalaz.concurrent.Task
import scalaz.stream.Process

import streamz.akka.camel._

object CSVReader extends App{
  println("Testing..")

  implicit val system = ActorSystem("example")

  val p: Process[Task, Unit] =
    // receive from endpoint
    receive[String]("file:///Users/vpandya/cargotel/persistence-odin/filecsv.txt")
     .intersperse("\n")
      // in-only message exchange with endpoint and continue stream with in-message
      .sendW("seda:q3")
      // in-only message exchange with endpoint and continue stream with out-message
      //.request[Int]("bean:service?method=length")
      // in-only message exchange with endpoint
      .send("seda:q2")

  // create concurrent task from process
  val t: Task[Unit] = p.run

  // run task (side effects only here) ...
  t.run

}