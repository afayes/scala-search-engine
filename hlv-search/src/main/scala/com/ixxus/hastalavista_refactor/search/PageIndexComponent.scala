package com.ixxus.hastalavista_refactor.search

import org.slf4j.LoggerFactory

/**
  * todo add comments.
  */
trait PageIndexComponent {
  val pageIndex:PageIndex
}

trait PageIndex {
  def update(url:String, page:Page)
  def apply(url:String):Option[Page]
  def getAll():Seq[Page]
}

class PageIndexUsingHashMap extends PageIndex {

  private val logger = LoggerFactory.getLogger(classOf[PageIndexUsingHashMap])

  override def update(url: String, page: Page): Unit = {
    PageIndexUsingHashMap.data = PageIndexUsingHashMap.data + (url -> page)
    logger.debug(s"Added ${page.url} to index")
  }

  override def apply(url: String): Option[Page] = {
    PageIndexUsingHashMap.data.get(url)
  }

  override def getAll(): Seq[Page] = PageIndexUsingHashMap.data.values.toSeq
}

object PageIndexUsingHashMap {
  var data = Map[String, Page]()
  def apply():PageIndexUsingHashMap = new PageIndexUsingHashMap
}



