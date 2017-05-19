package com.ixxus.hastalavista.analytics

import java.util.concurrent.atomic.AtomicBoolean
import java.util.{Date, Timer, TimerTask}
import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue, LinkedBlockingQueue, TimeUnit}

import com.ixxus.hastalavista.search.PageIndexComponent
import org.slf4j.LoggerFactory

import scala.concurrent.Future
/**
  * todo add comments.
  */
trait AnalyticsServiceComponent {

    this: PageIndexComponent =>

    val analyticsService: AnalyticsService

    trait AnalyticsService {
        def updateLastRetrievalDate(url: String, date: Date)

        def updateClickCount(url: String)

        def apply(url: String): Option[Analytics]

        def getAll(): Seq[Analytics]
    }

    class AnalyticServiceUsingHashMap private(private var data: Map[String, Analytics], private var clickCountQueue:BlockingQueue[String]) extends AnalyticsService {

        private val logger = LoggerFactory.getLogger(classOf[AnalyticServiceUsingHashMap])

        val stopPolling = new AtomicBoolean(false)

        import scala.concurrent.ExecutionContext.Implicits.global

        global.execute(() => {
            while (stopPolling.get() == false) {
                val clickUrl = clickCountQueue.poll(1000, TimeUnit.MILLISECONDS)
                if (clickUrl != null) {
                    updateClickCountImpl(clickUrl)
                }
            }
        })

        override def updateLastRetrievalDate(url: String, date: Date) {
            val existing = data.get(url)
            existing match {
                case Some(existingItem) =>
                    val existingRetrievalDate = existingItem.lastRetrievalDate
                    if (existingRetrievalDate == null) update(Analytics(url, date, existingItem.clickCount))
                    else if (date.compareTo(existingRetrievalDate) > 0) update(Analytics(url, date, existingItem.clickCount))

                case None => update(Analytics(url, date, 0))
            }
        }

        private def update(analytics: Analytics) {
            data += analytics.url -> analytics
        }


        override def updateClickCount(url:String) {
           val pageOption = pageIndex(url)
            pageOption match {
                case Some(page) =>  Future {
                    clickCountQueue.put(page.url)
                }
                case None => throw new IllegalArgumentException(s"$url has not been indexed")
            }
        }

        private def updateClickCountImpl(url:String) {
            val existing = data.get(url)
            existing match {
                case Some(existingItem) =>
                    val clickCount = existingItem.clickCount + 1
                    update(Analytics(existingItem.url, existingItem.lastRetrievalDate, clickCount))
                    logger.info(s"Updated click count for $url to $clickCount")
                case None => update(Analytics(url, new Date(), 1))
            }
        }

        override def apply(url: String): Option[Analytics] = data.get(url)

        override def getAll(): Seq[Analytics] = data.values.toSeq.sortWith((analytics1, analytics2) => analytics1.clickCount > analytics2.clickCount)

    }

    object AnalyticServiceUsingHashMap {
        def apply(data: Map[String, Analytics], clickCountQueue:BlockingQueue[String]): AnalyticServiceUsingHashMap = new AnalyticServiceUsingHashMap(data, clickCountQueue)
    }
}
