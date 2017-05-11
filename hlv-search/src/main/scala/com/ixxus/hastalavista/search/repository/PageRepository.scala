package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{CommandItem, PageCommandItem, PageKey, PageQueryItem}
import org.springframework.stereotype

/**
  * todo add comments.
  */
class PageRepository extends Repository[PageKey, PageCommandItem, PageQueryItem]{

  private var data = Map[String, PageQueryItem]()

  override def save(item: PageCommandItem): Unit = {
    // todo manage concurrency
    data = data + (item.url -> PageQueryItem(item.url, item.content, item.creationDate))
  }

  override def get(url: PageKey): PageQueryItem = ???

  override def getAll(): Seq[PageQueryItem] = {
    data.values.toSeq
  }
}
