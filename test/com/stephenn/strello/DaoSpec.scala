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

@RunWith(classOf[JUnitRunner])
class DaoSpec extends Specification {
  def dao = CardController.dao
  
  def fakeApp[T](block:  => T):T = running(FakeApplication(additionalConfiguration = inMemoryDatabase()))(block)
  
  "Dao" should {
    "create" in fakeApp {
      dao.list.isEmpty must equalTo(true)
      
      val in = Card(-1, "test note")
      val out = dao.create(in)
      
      out.id must not equalTo(-1)
    }
    
    "list" in fakeApp {
      dao.list.isEmpty must equalTo(true)
      
      dao.create(Card(-1, "test note"))
      
      val l = dao.list
      l.size must equalTo(1)
      l(0).title must equalTo("test note")
    }
    
    "update" in fakeApp {
      val created = dao.create(Card(-1, "test note"))
      dao.update(created.copy(title = "updated note"))
      
      val l = dao.list
      l.size must equalTo(1)
      l(0).title must equalTo("updated note")
    }
    
    "delete" in fakeApp {
      val created = dao.create(Card(-1, "test note"))
      val res = dao.delete(created.id)
      
      res must equalTo(true)
      
      
      dao.list.isEmpty must equalTo(true)
    }
    
    "delete on unsaved" in fakeApp {
      val res = dao.delete(99l)
      res must equalTo(false)
    }
  }
}