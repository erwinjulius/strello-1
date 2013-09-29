package com.stephenn.strello

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._

trait Dao[T <: KeyedEntity[Long]] {
  val table: Table[T]
  def list = inTransaction { table.iterator.toList }
  def create(t: T) = inTransaction { table.insert(t) }
  def update(t: T) = inTransaction { table.update(t) }
  def delete(t: T) = inTransaction { table.delete(t.id) }
}