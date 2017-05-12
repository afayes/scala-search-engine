package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model._
import com.ixxus.hastalavista.search.repository.Repository

/**
  * todo add comments.
  */
trait RepositoryService {

  def save(item:CommandItem)
  def get(key:KeyItem):Option[QueryItem]
  def getAll(itemType: ItemTypes.Value):Seq[QueryItem]
}
