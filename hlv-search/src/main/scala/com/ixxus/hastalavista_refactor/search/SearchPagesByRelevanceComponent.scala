package com.ixxus.hastalavista_refactor.search

/**
  * todo add comments.
  */
trait SearchPagesByRelevanceComponent {

  this: PageIndexComponent =>

  trait SearchPagesByRelevance {
    def search(query:String):Seq[SearchResultItem]
  }

  val searchPagesByRelevance: SearchPagesByRelevance

  class SearchPagesByRelevanceImpl extends SearchPagesByRelevance {
    override def search(query: String): Seq[SearchResultItem] = {
      val pages = pageIndex.getAll()
      null
      // todo finish impl
    }
  }

  object SearchPagesByRelevanceImpl {
    def apply():SearchPagesByRelevanceImpl = new SearchPagesByRelevanceImpl
  }

}
