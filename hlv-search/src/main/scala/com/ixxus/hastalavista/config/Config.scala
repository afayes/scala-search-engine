package com.ixxus.hastalavista.config

import java.util.concurrent.LinkedBlockingQueue

import com.ixxus.hastalavista.analytics.AnalyticsServiceComponent
import com.ixxus.hastalavista.search._

/**
  * todo add comments.
  */
object Config {

    val components = new SearchPagesByRelevanceComponent with PageIndexComponent with RetrievePagesByRetrievalDateComponent with AnalyticsServiceComponent {
        override val searchPagesByRelevance = SearchPagesByRelevanceUsingRegEx()
        override val pageIndex:PageIndex = PageIndexUsingHashMap(Map())
        override val analyticsService:AnalyticsService = AnalyticServiceUsingHashMap(Map(), new LinkedBlockingQueue[String])
        override val retrievePagesByRetrievalDate: RetrievePagesByRetrievalDate = RetrievePagesByRetrievalDateImpl()
    }
}
