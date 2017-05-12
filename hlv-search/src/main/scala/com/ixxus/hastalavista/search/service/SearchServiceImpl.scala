package com.ixxus.hastalavista.search.service

import java.util.Date

import com.ixxus.hastalavista.search.model.{ItemTypes, PageQueryItem}

/**
  * todo add comments.
  */
class SearchServiceImpl extends SearchService with SharedRepositoryServiceProvider{

  override def search(query: String): Seq[PageQueryItem] = {
    search(query, (p1, p2) => {
      val (p1MatchCount, p1MatchDistance) = getRelevancy(p1, query)
      val (p2MatchCount, p2MatchDistance) = getRelevancy(p2, query)

      if (p1MatchCount > p2MatchCount) true
      if (p1MatchCount < p2MatchCount) false
      p1MatchDistance > p2MatchDistance
    }, Option(p => {
      val (matchCount, matchDistance) = getRelevancy(p, query)
      matchCount > 0 || matchDistance < Int.MaxValue
    }))
  }

  override def searchRankedByLastRetrievalDate(query: String): Seq[PageQueryItem] = ???

  override def searchRankedCreationDate(query: String): Seq[PageQueryItem] = ???

  private def search(query:String, sortFunction: (PageQueryItem, PageQueryItem) => Boolean, filter:Option[(PageQueryItem)=>Boolean] = None): Seq[PageQueryItem] = {
    val pages = repositoryService.getAll(ItemTypes.Page).asInstanceOf[Seq[PageQueryItem]]
    val pagesFiltered = filter match {
      case Some(func) => pages.filter(func)
      case None => pages
    }

    pagesFiltered.sortWith(sortFunction)
  }

  private def getRelevancy(page:PageQueryItem, query:String):(Int, Int) = {
    val pattern = query.r
    val matchCount = pattern.findAllMatchIn(page.content).size
    val matchDistance = if (matchCount == 0) getMatchDistance(query, page.content) else Int.MaxValue
    (matchCount, matchDistance)
  }

  private def getMatchDistance(query: String, content: String):Int = {
    // todo implelment
    Int.MaxValue
  }
}
