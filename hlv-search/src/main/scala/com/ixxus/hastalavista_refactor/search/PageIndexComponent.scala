package com.ixxus.hastalavista_refactor.search

import org.slf4j.LoggerFactory

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

    private val logger = LoggerFactory.getLogger(classOf[PageIndexUsingHashMap])

    override def update(url: String, page: Page): Unit = {
      PageIndexData.data = PageIndexData.data + (url -> page)
      logger.debug(s"Added ${page.url} to index")
    }

    override def apply(url: String): Option[Page] = {
      PageIndexData.data.get(url)
    }

    override def getAll(): Seq[Page] = PageIndexData.data.values.toSeq
  }

  object PageIndexUsingHashMap {
    def apply():PageIndexUsingHashMap = new PageIndexUsingHashMap
  }
}

object PageIndexData {
  var data = Map[String, Page]()
}



