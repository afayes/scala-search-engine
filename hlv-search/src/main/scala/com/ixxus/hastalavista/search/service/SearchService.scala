package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model.{PageQueryItem, SearchResultItem}

/**
  * todo add comments.
  */
trait SearchService {

  def search(query:String):Seq[SearchResultItem]
  def getPagesByLastRetrievalDate():Seq[SearchResultItem]
  def getPagesCreationDate(query:String):Seq[SearchResultItem]
}
