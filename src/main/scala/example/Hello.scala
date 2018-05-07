package example

import org.apache.logging.log4j.scala.Logging

object Hello extends Greeting with App with Logging {
  logger.info(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
