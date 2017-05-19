package com.ixxus.hastalavista.search

import java.util.Date

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
case class SearchResultItem(@BeanProperty var url:String,
                            @BeanProperty var matchCount:Int,
                            @BeanProperty var matchDistance:Int,
                            @BeanProperty var lastRetrievalDate:Date,
                            @BeanProperty var creationDate:Date)
