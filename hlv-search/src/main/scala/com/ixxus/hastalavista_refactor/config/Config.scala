package com.ixxus.hastalavista_refactor.config

import com.ixxus.hastalavista_refactor.analytic.{AnalyticServiceUsingHashMap, AnalyticsService, AnalyticsServiceComponent}
import com.ixxus.hastalavista_refactor.search._

/**
  * todo add comments.
  */
object Config {

    val components = new SearchPagesByRelevanceComponent with PageIndexComponent with RetrievePagesByRetrievalDateComponent with AnalyticsServiceComponent {
        override val searchPagesByRelevance = SearchPagesByRelevanceUsingRegEx()
        override val pageIndex:PageIndex = PageIndexUsingHashMap()
        override val analyticsService:AnalyticsService = AnalyticServiceUsingHashMap()
        override val retrievePagesByRetrievalDate: RetrievePagesByRetrievalDate = RetrievePagesByRetrievalDateImpl()
    }
}
