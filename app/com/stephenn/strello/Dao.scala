package com.stephenn.strello

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._

trait Dao[T <: KeyedEntity[Long]] {
  val table: Table[T]

  def get(id: Long) = inTransaction { table.where(t => t.id === id).headOption }
  def list = inTransaction { table.iterator.toList }
  def create(t: T) = inTransaction { table.insert(t) }
  def update(t: T) = inTransaction {
    if (table.exists(x => x.id == t.id)) {
      table.update(t)
      true
    } else {
      false
    }
  }
  def delete(id: Long) = inTransaction { table.delete(id) }
}