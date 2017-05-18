package com.ixxus.hastalavista_refactor.analytic

import java.util.Date

/**
  * todo add comments.
  */
class Analytics(val url:String, val lastRetrievalDate:Date, val clickCount:Int)

object Analytics {
  def apply(url:String, lastRetrievalDate:Date, clickCount:Int):Analytics = new Analytics(url, lastRetrievalDate, clickCount)
}
