package com.ixxus.hastalavista.search

import java.util.Date

/**
  * todo add comments.
  */
  class Page(val url: String, val content:String, val creationDate:Date) {
}


object Page {

  def apply(url:String, content:String, creationDate:Date): Page = {
    new Page(url, content, creationDate)
    }
}
