package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{CommandItem, ItemTypes, KeyItem, QueryItem}

/**
  * todo add comments.
  */
trait Repository[K <: KeyItem, C <:CommandItem, Q <: QueryItem] {

  def save(item:C):Unit = {
  }
  def get(url:K):Q
  def getAll():Seq[Q]
}
