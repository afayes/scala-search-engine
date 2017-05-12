package com.ixxus.hastalavista.search.repository

import com.ixxus.hastalavista.search.model.{AnalyticKey, AnalyticQueryItem, AnalyticRetrievalDateCommandItem, CommandItem}
import org.springframework.stereotype


/**
  * todo add comments.
  */
class AnalyticRepository extends Repository [AnalyticKey, CommandItem,  AnalyticQueryItem]{

  var data = Map[String, AnalyticQueryItem]()


  override def save(item: CommandItem): Unit = item match {
    case a: AnalyticRetrievalDateCommandItem => saveRetrievalDateCommandItem(a)
  }

  def saveRetrievalDateCommandItem(item: AnalyticRetrievalDateCommandItem):Unit = {
    val existing = data.get(item.url)
    existing match {
      case Some(existingItem) => {
        val existingRetrievalDate = existingItem.retrievalDate
        // todo retrieval date may be null once we do click stuff
        if (item.retrievalDate.compareTo(existingRetrievalDate) > 0) data = data + (existingItem.url -> existingItem.copy(retrievalDate = item.retrievalDate))
      }
      case None => data = data + (item.url -> AnalyticQueryItem(item.url, item.retrievalDate, 0))
    }
  }


  override def get(url: AnalyticKey): AnalyticQueryItem = ???

  override def getAll(): Seq[AnalyticQueryItem] = ???
}
