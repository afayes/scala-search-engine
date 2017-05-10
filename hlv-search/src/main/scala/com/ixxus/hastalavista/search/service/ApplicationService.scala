package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model.{CommandItem, KeyItem, PageQueryItem, QueryItem}
import com.ixxus.hastalavista.search.repository.Repository

/**
  * todo add comments.
  */
trait ApplicationService extends Repository [KeyItem, CommandItem, QueryItem]{

  def search(query:String):Seq[PageQueryItem]

}
