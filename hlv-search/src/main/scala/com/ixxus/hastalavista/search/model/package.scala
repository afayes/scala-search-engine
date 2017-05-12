package com.ixxus.hastalavista.search

import java.util.Date

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
package object model {
  abstract class CommandItem
  case class PageCommandItem(url: String, content:String, creationDate:Date) extends CommandItem
  case class AnalyticRetrievalDateCommandItem(url:String, retrievalDate:Date) extends CommandItem
  case class AnalyticClickCommandItem(url:String) extends CommandItem

  abstract class QueryItem
  case class PageQueryItem(url: String, content:String, creationDate:Date) extends QueryItem
  case class AnalyticQueryItem(url:String, retrievalDate:Date, clickCount:Int) extends QueryItem

  abstract class KeyItem
  case class PageKey(url:String) extends KeyItem
  case class AnalyticKey(url:String) extends KeyItem

  class Page() {

    @BeanProperty
    var url: String = _

    @BeanProperty
    var content:String = _

    @BeanProperty
    var creationDate:Date = _
  }

  object ItemTypes extends Enumeration {
    val Page, Analytic = Value
  }

  object Page {
    def apply(url:String, content:String, creationDate:Date):Page = {
      val page = new Page
      page.url = url
      page.content = content
      page.creationDate = creationDate
      page
    }
  }

  class SearchResultItem {

    @BeanProperty
    var url:String = _

    @BeanProperty
    var matchCount:Int = _

    @BeanProperty
    var matchDistance:Int = _

    @BeanProperty
    var lastRetrievalDate:Date = _

    @BeanProperty
    var creationDate:Date = _


    override def toString = s"SearchResultItem($url, $matchCount, $matchDistance, $lastRetrievalDate, $creationDate)"
  }

  object SearchResultItem {
    def apply(url:String, matchCount:Int, mathDistance:Int, lastRetrievalDate:Date, creationDate:Date):SearchResultItem = {
      val p = new SearchResultItem
      p.url = url
      p.matchCount = matchCount
      p.matchDistance = mathDistance
      p.lastRetrievalDate = lastRetrievalDate
      p.creationDate = creationDate
      p
    }
  }
}
