package com.ixxus.hastalavista_refactor.analytic

import java.util.Date

import com.ixxus.hastalavista.search.model.AnalyticQueryItem

/**
  * todo add comments.
  */
trait AnalyticsServiceComponent {

    val analyticsService: AnalyticsService

    trait AnalyticsService {
        def updateLastRetrievalDate(url: String, date: Date)

        def updateClickCount(url: String)

        def apply(url: String): Analytics

        def getAll(): Seq[Analytics]
    }

    class AnalyticServiceUsingHashMap extends AnalyticsService {

        override def updateLastRetrievalDate(url: String, date: Date): Unit = {
            val existing = AnalyticsData.data.get(url)
            existing match {
                case Some(existingItem) => {
                    val existingRetrievalDate = existingItem.lastRetrievalDate
                    if (existingRetrievalDate == null) update(Analytics(url, date, 0))
                    else {
                        if (date.compareTo(existingRetrievalDate) > 0) update(Analytics(url, date, existingItem.clickCount))
                    }
                }
                case None => update(Analytics(url, date, 0))
            }
        }

        private def update(analytics: Analytics): Unit = {
            AnalyticsData.data = AnalyticsData.data + (analytics.url -> analytics)
        }

        override def updateClickCount(url: String): Unit = ???

        override def apply(url: String): Analytics = ???

        override def getAll(): Seq[Analytics] = {
            AnalyticsData.data.values.toSeq
        }
    }

    object AnalyticServiceUsingHashMap {
        def apply():AnalyticServiceUsingHashMap = new AnalyticServiceUsingHashMap
    }

}

object AnalyticsData {
    var data = Map[String, Analytics]()
}


