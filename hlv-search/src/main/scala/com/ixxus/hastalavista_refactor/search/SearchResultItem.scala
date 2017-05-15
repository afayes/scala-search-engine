package com.ixxus.hastalavista_refactor.search

import java.util.Date

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
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
