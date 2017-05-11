package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{AnalyticKey, AnalyticQueryItem, CommandItem}
import org.springframework.stereotype


/**
  * todo add comments.
  */
class AnalyticRepository extends Repository [AnalyticKey, CommandItem,  AnalyticQueryItem]{

  override def save(item: CommandItem): Unit = ???

  override def get(url: AnalyticKey): AnalyticQueryItem = ???

  override def getAll(): Seq[AnalyticQueryItem] = ???
}
