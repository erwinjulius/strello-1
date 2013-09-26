package com.stephenn.strello

import org.squeryl.Table
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.json.Format
import org.squeryl.KeyedEntity

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._

import play.api.data._
import play.api.data.Forms._

object CardController extends Controller{
  
  val dao = new Dao[Card] {
    val table = AppDB.cards
  }
  
  def getTemplate = Action {
    Ok(html.cards(dao.list))
  }
  
  
  val newCardForm = Form(
      tuple(
          "title" -> text
          )
      
      )
  
  
  
}

trait Dao[T <: KeyedEntity[Long]] {
  val table: Table[T]
  def list = inTransaction { table.iterator.toList }
}