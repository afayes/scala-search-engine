package com.ixxus.hastalavista.search

import java.util.Date

import com.ixxus.hastalavista.analytics.AnalyticsServiceComponent

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * todo add comments.
  */
trait SearchPagesByRelevanceComponent {

    this: PageIndexComponent with AnalyticsServiceComponent =>

    trait SearchPagesByRelevance {
        def search(query: String): Future[Seq[SearchResultItem]]
    }

    val searchPagesByRelevance: SearchPagesByRelevance

    class SearchPagesByRelevanceUsingRegEx extends SearchPagesByRelevance {

        override def search(query: String): Future[Seq[SearchResultItem]] = {
            val pages = pageIndex.getAll()

            val searchResultsFuture = Future
                .traverse(pages)(p => Future {
                    val (matchCount, matchDistance) = getRelevancy(p, query)
                    SearchResultItem(p.url, matchCount, matchDistance, null, null)
                })

            searchResultsFuture
                .flatMap(searchResults => Future {
                val filtered = searchResults.filter(resultItem => resultItem.matchCount > 0 || resultItem.matchDistance < Int.MaxValue)
                updateAnalytics(filtered)
                filtered.sortWith((resultItem1: SearchResultItem, resultItem2: SearchResultItem) => {
                    if (resultItem1.matchCount > resultItem2.matchCount) true
                    else if (resultItem1.matchCount < resultItem2.matchCount) false
                    else resultItem1.matchDistance > resultItem2.matchDistance
                })
            })
        }

        private def getRelevancy(page: Page, query: String): (Int, Int) = {
            val pattern = query.r
            val matchCount = pattern.findAllMatchIn(page.content).size
            val matchDistance = if (matchCount == 0) getMatchDistance(query, page.content) else Int.MaxValue
            (matchCount, matchDistance)
        }

        private def getMatchDistance(query: String, content: String): Int = {
            // todo implelment
            Int.MaxValue
        }

        def updateAnalytics(filtered: Seq[SearchResultItem]) = {
            val date = new Date
            filtered.foreach(searchResultItem => Future {
                analyticsService.updateLastRetrievalDate(searchResultItem.url, date)
            })
        }
    }

    object SearchPagesByRelevanceUsingRegEx {
        def apply(): SearchPagesByRelevanceUsingRegEx = new SearchPagesByRelevanceUsingRegEx
    }
}
