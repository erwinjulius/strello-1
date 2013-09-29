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
class DaoSpec extends Specification {
  
  
  "Dao" should {
    "insert the item" in running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      CardController.dao.list.size must equalTo(0)
    }
  }
}