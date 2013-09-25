package com.stephenn.strello

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._

object AppDB extends Schema {
  val cards = table[Card]("cards")

}