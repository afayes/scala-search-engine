package com.ixxus.hastalavista.search.service

import java.util.Date

import com.ixxus.hastalavista.search.model.{CommandItem, _}
import com.ixxus.hastalavista.search.repository.{AnalyticRepository, PageRepository, Repository}

/**
  * todo add comments.
  */
class ApplicationServiceImpl extends ApplicationService {

  private val pageRepository = new PageRepository

  private val analyticRepository = new AnalyticRepository

  override def save(item: CommandItem): Unit = item match {
    case p:PageCommandItem => pageRepository.save(p)
    case a:AnalyticRetrievalDateCommandItem => analyticRepository.save(a)
    case a:AnalyticClickCommandItem => analyticRepository.save(a)
  }

  override def get(url: KeyItem): QueryItem = ???

  override def getAll(itemType: ItemTypes.Value): Seq[QueryItem] = itemType match {
    case ItemTypes.Page => pageRepository.getAll()
  }

  override def search(query: String): Seq[PageQueryItem] = {
    pageRepository.getAll().sortWith((p1, p2) => {
      getPageRankData(query, p1).compare(getPageRankData(query, p2)) > 0
    })
  }

  private def getPageRankData(query:String, page: PageQueryItem): PageRankData = {
    val pattern = query.r
    val matchCount = pattern.findAllMatchIn(page.content).size
    val matchDistance = if (matchCount == 0) getMatchDistance(query, page.content) else 0
    val lastRetrievalDate = getPageLastRetrievalDate(page)
    val creationDate = page.creationDate

    PageRankData(matchCount, matchDistance, lastRetrievalDate, creationDate)
  }

  // todo
  private def getMatchDistance(query: String, content: String):Int = ???

  // todo
  private def getPageLastRetrievalDate(page: PageQueryItem) = ???


  class PageRankData(val queryExactMatchCount:Int, val queryMatchDistance:Int, val retrievalDate:Date, val creationDate:Date) extends Ordered[PageRankData]{
    override def compare(that: PageRankData): Int = {
      if (queryExactMatchCount > that.queryExactMatchCount) 1
      if (queryExactMatchCount < that.queryExactMatchCount) -1
      if (queryMatchDistance > that.queryMatchDistance) 1
      if (queryMatchDistance < that.queryMatchDistance) -1
      // null check or use Option
      if (retrievalDate.compareTo(that.retrievalDate) > 0) 1
      if (retrievalDate.compareTo(that.retrievalDate) < 0) -1
      creationDate.compareTo(that.creationDate )
    }
  }

  object PageRankData {
    def apply (queryExactMatchCount:Int, queryMatchDistance:Int, retrievalDate:Date, creationDate:Date):PageRankData = {
      new PageRankData(queryExactMatchCount, queryMatchDistance, retrievalDate, creationDate)
    }
  }

}
