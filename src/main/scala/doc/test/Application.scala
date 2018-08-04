package doc.test

import doc.test.a.ProjA
import doc.test.b.ProjB


/** The main application
  *
  * ==Overview==
  *
  * The main class to use is [[doc.test.Application]], as so
  * {{{
  * val a = new ProjA
  * println(s"value of A: \\${a.value}")
  * }}}
  *
  * @version 0.0
  * @note This is a note.
  *
  */
object Application extends App {

  val a = new ProjA
  val b = new ProjB

  println("This is the app")
  println(s"value of A: ${a.value}")
  println(s"value of B: ${b.value}")
}
