package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model.{CommandItem, _}
import com.ixxus.hastalavista.search.repository.{AnalyticRepository, PageRepository, Repository}
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Service

/**
  * todo add comments.
  */
class ApplicationServiceImpl extends ApplicationService {

  private val pageRepository = new PageRepository

  private val analyticRepository = new AnalyticRepository

  override def save(item: CommandItem): Unit = item match {
    case p:PageCommandItem => pageRepository.save(p)
    case a:AnalyticRetrievalDateCommandItem => analyticRepository.save(a)
    case a:AnalyticClickCommandItem => analyticRepository.save(a)
  }

  override def get(url: KeyItem): QueryItem = ???

  override def getAll(itemType: ItemTypes.Value): Seq[QueryItem] = itemType match {
    case ItemTypes.Page => pageRepository.getAll()
  }

  override def search(query: String): Seq[PageQueryItem] = ???
}
