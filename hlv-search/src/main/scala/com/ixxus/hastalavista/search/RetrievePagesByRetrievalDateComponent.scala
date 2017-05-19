package com.ixxus.hastalavista.search

import com.ixxus.hastalavista.analytics.AnalyticsServiceComponent

/**
  * todo add comments.
  */
trait RetrievePagesByRetrievalDateComponent {
  this: AnalyticsServiceComponent =>

  trait RetrievePagesByRetrievalDate {
    def getPages():Seq[SearchResultItem]
  }

  val retrievePagesByRetrievalDate:RetrievePagesByRetrievalDate

  class RetrievePagesByRetrievalDateImpl extends RetrievePagesByRetrievalDate {

    override def getPages(): Seq[SearchResultItem] =
      analyticsService.getAll().filter(_.lastRetrievalDate != null).sortWith((a1, a2) => {
        a1.lastRetrievalDate.compareTo(a2.lastRetrievalDate) > 0
      }).map(a => SearchResultItem(a.url, 0, 0, a.lastRetrievalDate, null))
  }

  object RetrievePagesByRetrievalDateImpl {
    def apply():RetrievePagesByRetrievalDateImpl = new RetrievePagesByRetrievalDateImpl
  }
}
