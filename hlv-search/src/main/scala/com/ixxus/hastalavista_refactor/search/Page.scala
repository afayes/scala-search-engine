package com.ixxus.hastalavista_refactor.search

import java.util.Date

/**
  * todo add comments.
  */
  class Page(val url: String, val content:String, val creationDate:Date) {
  this:PageIndexComponent =>

  def index() = pageIndex(url) = this
}


object Page {

  def apply(url:String, content:String, creationDate:Date): Page with PageIndexComponent =
    new Page(url, content, creationDate) with PageIndexComponent {
      val pageIndex = new PageIndexUsingHashMap()
    }
}
