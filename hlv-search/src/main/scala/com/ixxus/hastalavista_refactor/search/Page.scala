package com.ixxus.hastalavista_refactor.search

import java.util.Date

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
  class Page() {

    this: PageIndexComponent =>

    @BeanProperty
    var url: String = _

    @BeanProperty
    var content:String = _

    @BeanProperty
    var creationDate:Date = _

  def index() = pageIndex(url) = this
  }


object Page {

  def apply(url:String, content:String, creationDate:Date): Page = {
    val p = new Page with PageIndexComponent {
      val pageIndex = new PageIndexImpl()
    }
    p.url = url
    p.content = content
    p.creationDate = creationDate
    p
  }
}
