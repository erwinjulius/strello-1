package com.stephenn.strello

import play.api.mvc.Controller
import play.api.libs.json._
import play.api.mvc.Action
import org.squeryl.KeyedEntity
import play.api.mvc.SimpleResult

trait CrudController[T <: KeyedEntity[Long]] extends Controller {
  def dao: Dao[T]
  
  implicit def format: Format[T]
  
  def list = Action { implicit request =>
    Ok(Json.toJson(dao.list))
  }
  
  def withT(block: (T) => SimpleResult) = Action(parse.json)  { implicit request =>
    Json.fromJson[T](request.body).fold(
      invalid = { errors => BadRequest("" + errors) },
      valid = block(_)
      )
  }
  
  def create = withT { t =>
    val created = dao.create(t)
    Ok(Json.toJson(created))
  }
  
  def update(id: Long) = withT { t =>
    dao.update(t) match {
      case true => Accepted
      case false => NotFound
    }
  }
  
  def delete(id: Long) = Action(parse.anyContent) { implicit request =>
    dao.delete(id) match {
      case true => Ok
      case _ => NotFound
    }
  }
}