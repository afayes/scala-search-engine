package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{CommandItem, PageCommandItem, PageKey, PageQueryItem}
import org.springframework.stereotype

/**
  * todo add comments.
  */
@stereotype.Repository("pageRepository")
class PageRepository extends Repository[PageKey, PageCommandItem, PageQueryItem]{

  private var data = Map[String, PageQueryItem]

  override def save(item: PageCommandItem): Unit = {
  }

  override def get(url: PageKey): PageQueryItem = ???

  override def getAll(): Seq[PageQueryItem] = ???
}
