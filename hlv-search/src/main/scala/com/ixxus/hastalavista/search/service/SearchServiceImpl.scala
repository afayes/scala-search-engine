package com.ixxus.hastalavista.search.service

import java.util.Date

import scala.concurrent.duration._
import com.ixxus.hastalavista.search.model._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * todo add comments.
  */
class SearchServiceImpl extends SearchService with SharedRepositoryServiceProvider{

  override def search(query: String): Seq[SearchResultItem] = {

    val searchResultsRetrievalFunc = (pages:Seq[PageQueryItem]) => {
      val searchResultsFuture = Future.traverse(pages)(p => Future{
        val (matchCount, matchDistance) = getRelevancy(p, query)
        SearchResultItem(p.url, matchCount, matchDistance, null, null)
      })

      Await.result(searchResultsFuture, 10.seconds)

      val searchResults = searchResultsFuture.value.get.get
      val filtered =  searchResults.filter(s => s.matchCount > 0 || s.matchDistance < Int.MaxValue)
      filtered
    }

    val sortFunc = (s1:SearchResultItem, s2:SearchResultItem) => {
      if (s1.matchCount > s2.matchCount) true
      else if (s1.matchCount < s2.matchCount) false
      else s1.matchDistance > s2.matchDistance
    }

    search(searchResultsRetrievalFunc, sortFunc)
  }

  private def search(searchResultsRetrievalFunc:(Seq[PageQueryItem] => Seq[SearchResultItem]),
                     sortFunc:(SearchResultItem, SearchResultItem) => Boolean): Seq[SearchResultItem] = {

    val pages = repositoryService.getAll(ItemTypes.Page).asInstanceOf[Seq[PageQueryItem]]
    val searchResults = searchResultsRetrievalFunc(pages);
    searchResults.toList.sortWith(sortFunc)
  }

  override def getPagesCreationDate(query: String): Seq[SearchResultItem] = ???

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

  override def getPagesByLastRetrievalDate(): Seq[SearchResultItem] = {

    // todo fix
    // get analytic items directly
    val searchResultsRetrievalFunc = (pages:Seq[PageQueryItem]) => {
      val searchResultsFuture = Future.traverse(pages)(p => Future{
        val lastRetrievalDate = getLastRetrievalDate(p)
        SearchResultItem(p.url, 0, 0, lastRetrievalDate, null)
      })

      Await.result(searchResultsFuture, 10.seconds)

      val searchResults = searchResultsFuture.value.get.get
      val filtered =  searchResults.filter(s => s.lastRetrievalDate != null)
      filtered
    }

    val sortFunc = (s1:SearchResultItem, s2:SearchResultItem) => {
      s1.lastRetrievalDate.compareTo(s2.lastRetrievalDate) > 0
    }

    search(searchResultsRetrievalFunc, sortFunc)
  }

  private def updateLastRetrievalDateAnalytics(pagesSorted: Seq[PageQueryItem]):Unit = {
      val currentDate = new Date()
      pagesSorted.foreach(p =>
        Future{
          repositoryService.save(AnalyticRetrievalDateCommandItem(p.url, currentDate))
        }
      )
  }

  def getLastRetrievalDate(page: PageQueryItem):Date = {
    val analyticQueryItem = repositoryService.get(AnalyticKey(page.url)).asInstanceOf[Option[AnalyticQueryItem]]
    analyticQueryItem match {
      case Some(item) => item.retrievalDate
      case None => null
    }
  }
}
