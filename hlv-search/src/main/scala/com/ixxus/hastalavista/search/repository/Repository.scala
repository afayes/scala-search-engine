package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{KeyItem, CommandItem, QueryItem}

/**
  * todo add comments.
  */
trait Repository[K <: KeyItem, C <:CommandItem, Q <: QueryItem] {

  def save(item:C)
  def get(url:K):Q
  def getAll():Seq[Q]
}
