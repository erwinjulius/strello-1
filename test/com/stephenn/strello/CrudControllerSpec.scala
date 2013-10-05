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

    "insert" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val js = Json.parse("""{"id":-1, "title":"bla"}""")

      val res = ctrl.create(FakeRequest(POST, "", FakeHeaders(), js))
      status(res) must equalTo(200)
    }
    
    "delete handles 404" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val res = ctrl.delete(1)(FakeRequest(DELETE, ""))
      
      status(res) must equalTo(404)
    }
  }
}