package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model.{PageQueryItem, SearchResultItem}

/**
  * todo add comments.
  */
trait SearchService {

  def search(query:String):Seq[SearchResultItem]
  def searchRankedByLastRetrievalDate(query:String):Seq[PageQueryItem]
  def searchRankedCreationDate(query:String):Seq[PageQueryItem]
}
