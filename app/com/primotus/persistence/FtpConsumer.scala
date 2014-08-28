package com.primotus.persistence

import java.io.InputStream

import akka.actor.ActorSystem

import scalaz.concurrent.Task
import scalaz.stream._

import streamz.akka.camel._


object FtpConsumer extends App {
  implicit val system = ActorSystem("example")

 
  val endpointUri = "file:/Users/vpandya/?fileName=test4.txt&noop=true"

  val ftpLines: Process[Task, String] = for {
    is <- receive[InputStream](endpointUri)
    line <- io.linesR(is)
  } yield line
  

  val printUpper: Process[Task, Unit] = ftpLines
    // convert lines to upper case
    .map(_.toUpperCase)
    // write lines from all files to stdout
    .to(io.stdOutLines)

  // side effects here ...
  printUpper.run.run

  
}