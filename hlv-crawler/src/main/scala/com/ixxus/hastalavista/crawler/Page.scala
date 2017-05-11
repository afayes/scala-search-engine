package com.ixxus.hastalavista.crawler

import java.util.Date

import scala.beans.BeanProperty;

class Page() {

  @BeanProperty
  var url: String = _

  @BeanProperty
  var content:String = _

  @BeanProperty
  var creationDate:Date = _
}

object Page {
  def apply(url:String, content:String, creationDate:Date):Page = {
    val page = new Page
    page.url = url
    page.content = content
    page.creationDate = creationDate
    page
  }
}
