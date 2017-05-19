package com.ixxus.hastalavista.search

import java.util.Date

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
class SearchResultItem(@BeanProperty var url:String, @BeanProperty var matchCount:Int, @BeanProperty var matchDistance:Int, @BeanProperty var lastRetrievalDate:Date, @BeanProperty var creationDate:Date) {

  override def toString = s"SearchResultItem($url, $matchCount, $matchDistance, $lastRetrievalDate, $creationDate)"
}

object SearchResultItem {
  def apply(url:String, matchCount:Int, mathDistance:Int, lastRetrievalDate:Date, creationDate:Date):SearchResultItem = {
    new SearchResultItem(url, matchCount, mathDistance, lastRetrievalDate, creationDate)
  }
}
