package com.ixxus.hastalavista.search

import java.util.Date

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

  class Page(val url: String, val content:String, val creationDate:Date) {
    def this() = this(null, null, null)
  }
}
