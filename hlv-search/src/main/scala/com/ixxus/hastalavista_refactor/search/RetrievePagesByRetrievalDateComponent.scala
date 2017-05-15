package com.ixxus.hastalavista_refactor.search

/**
  * todo add comments.
  */
trait RetrievePagesByRetrievalDateComponent {
  this: PageIndexComponent =>

  trait RetrievePagesByRetrievalDate {
    def getPages():Seq[SearchResultItem]
  }

  val retrievePagesByRetrievalDate:RetrievePagesByRetrievalDate

  class RetrievePagesByRetrievalDateImpl extends RetrievePagesByRetrievalDate{
    override def getPages(): Seq[SearchResultItem] = ???
  }
}
