package com.stephenn.strello

import org.squeryl.Table
import play.api._
import play.api.mvc._
import play.api.libs.json._
import org.squeryl.KeyedEntity

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._



object CardController extends CrudController[Card] {
  
  val dao = new Dao[Card] {
    val table = AppDB.cards
  }
  
  override implicit val format = Json.format[Card]
}

