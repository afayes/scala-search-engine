package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{CommandItem, PageCommandItem, PageKey, PageQueryItem}
import org.springframework.stereotype

import scala.collection.mutable

/**
  * todo add comments.
  */
@stereotype.Repository("pageRepository")
class PageRepository extends Repository[PageKey, PageCommandItem, PageQueryItem]{

  private val data = mutable.Map[String, PageQueryItem]()

  override def save(item: PageCommandItem): Unit = {
    data += (item.url -> PageQueryItem(item.url, item.content, item.creationDate))
  }

  override def get(url: PageKey): PageQueryItem = ???

  override def getAll(): Seq[PageQueryItem] = {
    data.values.toSeq
  }
}
