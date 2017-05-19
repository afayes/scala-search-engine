package com.ixxus.hastalavista.search

import org.slf4j.LoggerFactory

/**
  * todo add comments.
  */
trait PageIndexComponent {

  val pageIndex:PageIndex

  trait PageIndex {
    def update(url:String, page:Page)
    def apply(url:String):Option[Page]
    def getAll():Seq[Page]
  }

  class PageIndexUsingHashMap private(private var data: Map[String, Page]) extends PageIndex {

    override def update(url: String, page: Page) {
      data += url -> page
      PageIndexUsingHashMap.logger.debug(s"Added ${page.url} to index")
    }

    override def apply(url: String): Option[Page] = data.get(url)

    override def getAll(): Seq[Page] = data.values.toSeq
  }

  object PageIndexUsingHashMap {
    private val logger = LoggerFactory.getLogger(classOf[PageIndexUsingHashMap])
    def apply(data:Map[String, Page]):PageIndexUsingHashMap = new PageIndexUsingHashMap(data)
  }
}




