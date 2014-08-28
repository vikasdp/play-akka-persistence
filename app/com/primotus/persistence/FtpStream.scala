package com.primotus.persistence

import scalaz.stream._
import scalaz.concurrent.Task
object FtpStream extends App{

  val converter: Task[Unit] =
    io.linesR("//Users/vpandya/test.txt")
      .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
      .map( _.toUpperCase)
      .intersperse("\n")
      .pipe(text.utf8Encode)
      .to(io.fileChunkW("/Users/vpandya/celsius.txt"))
      .run

  // at the end of the universe...
  val u: Unit = converter.run

}