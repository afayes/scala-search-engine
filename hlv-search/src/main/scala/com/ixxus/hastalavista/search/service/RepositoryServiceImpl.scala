package com.ixxus.hastalavista.search.service

import java.util.Date

import com.ixxus.hastalavista.search.model.{CommandItem, _}
import com.ixxus.hastalavista.search.repository.{AnalyticRepository, PageRepository, Repository}

/**
  * todo add comments.
  */
class RepositoryServiceImpl extends RepositoryService {

  private val pageRepository = new PageRepository

  private val analyticRepository = new AnalyticRepository

  override def save(item: CommandItem): Unit = item match {
    case p:PageCommandItem => pageRepository.save(p)
    case a:AnalyticRetrievalDateCommandItem => analyticRepository.save(a)
    case a:AnalyticClickCommandItem => analyticRepository.save(a)
  }

  override def get(key: KeyItem): Option[QueryItem] = {
    key match {
      case k:AnalyticKey => analyticRepository.get(k)
    }
  }

  override def getAll(itemType: ItemTypes.Value): Seq[QueryItem] = itemType match {
    case ItemTypes.Page => pageRepository.getAll()
  }
}
