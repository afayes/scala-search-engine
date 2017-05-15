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

  class PageIndexImpl extends PageIndex {

    override def update(url: String, page: Page): Unit = {
      PageIndexImpl.data = PageIndexImpl.data + (url -> page)
    }

    override def apply(url: String): Option[Page] = {
      PageIndexImpl.data.get(url)
    }

    override def getAll(): Seq[Page] = PageIndexImpl.data.values.toSeq
  }

  object PageIndexImpl {
    private var data = Map[String, Page]()
    def apply():PageIndexImpl = new PageIndexImpl
  }
}



