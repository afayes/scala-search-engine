package com.ixxus.hastalavista_refactor.search

/**
  * todo add comments.
  */
trait PageIndexComponent {

  trait PageIndex {
    def update(url:String, page:Page)
    def apply(url:String):Option[Page]
    def getAll():Seq[Page]
  }

  val pageIndex:PageIndex

  class PageIndexUsingHashMap extends PageIndex {

    override def update(url: String, page: Page): Unit = {
      PageIndexUsingHashMap.data = PageIndexUsingHashMap.data + (url -> page)
    }

    override def apply(url: String): Option[Page] = {
      PageIndexUsingHashMap.data.get(url)
    }

    override def getAll(): Seq[Page] = PageIndexUsingHashMap.data.values.toSeq
  }

  object PageIndexUsingHashMap {
    private var data = Map[String, Page]()
    def apply():PageIndexUsingHashMap = new PageIndexUsingHashMap
  }
}



