package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model._
import com.ixxus.hastalavista.search.repository.Repository

/**
  * todo add comments.
  */
trait ApplicationService {

  def save(item:CommandItem)
  def get(url:KeyItem):QueryItem
  def getAll(itemType: ItemTypes.Value):Seq[QueryItem]
  def search(query:String):Seq[PageQueryItem]
}