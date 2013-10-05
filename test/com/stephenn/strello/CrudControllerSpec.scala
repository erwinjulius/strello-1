package com.stephenn.strello

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

import play.api.libs.json.Json

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class CrudControllerSpec extends Specification {
  
  "CrudController" should {
    def ctrl = CardController
    def createdCard = ctrl.dao.create(Card(-1, "a"))
    
    "list" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val res = ctrl.list(FakeRequest(GET, ""))
      status(res) must equalTo(200)
    }    

    "insert" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val js = Json.parse("""{"id":-1, "title":""}""")

      val res = ctrl.create(FakeRequest(POST, "", FakeHeaders(), js))
      status(res) must equalTo(200)
    }
    
    "update" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val js = Json.parse(s"""{"id":${createdCard.id}, "title":"b"}""")

      val res = ctrl.update(createdCard.id)(FakeRequest(POST, "", FakeHeaders(), js))
      status(res) must equalTo(202)
    }
    
    "update not found" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val js = Json.parse(s"""{"id":-1, "title":"b"}""")

      val res = ctrl.update(-1l)(FakeRequest(POST, "", FakeHeaders(), js))
      status(res) must equalTo(404)
    }
    
    "delete found" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val res = ctrl.delete(createdCard.id)(FakeRequest(DELETE, ""))
      
      status(res) must equalTo(200)
    }
    
    "delete not found" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val res = ctrl.delete(1)(FakeRequest(DELETE, ""))
      
      status(res) must equalTo(404)
    }
  }
}