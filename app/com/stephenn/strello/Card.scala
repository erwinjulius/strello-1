package com.stephenn.strello

import org.squeryl.KeyedEntity

case class Card(id: Long, title: String) extends KeyedEntity[Long]