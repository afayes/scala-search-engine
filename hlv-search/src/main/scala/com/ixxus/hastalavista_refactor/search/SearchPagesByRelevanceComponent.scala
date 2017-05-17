package com.ixxus.hastalavista_refactor.search

import java.util.Date

import com.ixxus.hastalavista_refactor.analytic.AnalyticsServiceComponent

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global



/**
  * todo add comments.
  */
trait SearchPagesByRelevanceComponent {

  this: PageIndexComponent with AnalyticsServiceComponent =>

  trait SearchPagesByRelevance {
    def search(query:String):Seq[SearchResultItem]
  }

  val searchPagesByRelevance: SearchPagesByRelevance

  class SearchPagesByRelevanceUsingRegEx extends SearchPagesByRelevance {

    override def search(query: String): Seq[SearchResultItem] = {

      val searchResultsRetrievalFunc = (pages:Seq[Page]) => {
        val searchResultsFuture = Future.traverse(pages)(p => Future{
          val (matchCount, matchDistance) = getRelevancy(p, query)
          SearchResultItem(p.url, matchCount, matchDistance, null, null)
        })

        Await.result(searchResultsFuture, 10.seconds)

        val searchResults = searchResultsFuture.value.get.get
        val filtered =  searchResults.filter(s => s.matchCount > 0 || s.matchDistance < Int.MaxValue)
        updateAnalytics(filtered)
        filtered
      }

      val sortFunc = (s1:SearchResultItem, s2:SearchResultItem) => {
        if (s1.matchCount > s2.matchCount) true
        else if (s1.matchCount < s2.matchCount) false
        else s1.matchDistance > s2.matchDistance
      }

      search(searchResultsRetrievalFunc, sortFunc)
    }

    private def search(searchResultsRetrievalFunc:(Seq[Page] => Seq[SearchResultItem]),
                       sortFunc:(SearchResultItem, SearchResultItem) => Boolean): Seq[SearchResultItem] = {

      val pages = pageIndex.getAll()
      val searchResults = searchResultsRetrievalFunc(pages);
      searchResults.toList.sortWith(sortFunc)
    }

    private def getRelevancy(page:Page, query:String):(Int, Int) = {
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

    def updateAnalytics(filtered: Seq[SearchResultItem]) = {
        val date = new Date
        filtered.foreach(searchResultItem => Future {analyticsService.updateLastRetrievalDate(searchResultItem.url, date)})
    }

  object SearchPagesByRelevanceUsingRegEx {
    def apply():SearchPagesByRelevanceUsingRegEx = new SearchPagesByRelevanceUsingRegEx
  }

}
