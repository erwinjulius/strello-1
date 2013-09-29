package com.stephenn.strello

import play.api.mvc.Controller
import play.api.libs.json._
import play.api.mvc.Action
import org.squeryl.KeyedEntity

trait CrudController[T <: KeyedEntity[Long]] extends Controller {
  def dao: Dao[T]
  
  implicit def format: Format[T]
  
  def list = Action { implicit request =>
    Ok(Json.toJson(dao.list))
  }

  def create = Action(parse.json) { implicit request =>
    Json.fromJson[T](request.body).fold(
      invalid = { errors => BadRequest("" + errors) },
      valid = { t =>
        val created = dao.create(t);
        Ok(Json.toJson(created))
      })
  }
  
  def update = Action(parse.json) { implicit request =>
    Json.fromJson[T](request.body).fold(
      invalid = { errors => BadRequest("" + errors) },
      valid = { res => dao.update(res); Accepted })
  }
}