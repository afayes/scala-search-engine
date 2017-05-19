package com.ixxus.hastalavista.analytics

import java.util.Date

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
class Analytics(@BeanProperty val url:String, @BeanProperty val lastRetrievalDate:Date, @BeanProperty val clickCount:Int)

object Analytics {
  def apply(url:String, lastRetrievalDate:Date, clickCount:Int):Analytics = new Analytics(url, lastRetrievalDate, clickCount)
}
