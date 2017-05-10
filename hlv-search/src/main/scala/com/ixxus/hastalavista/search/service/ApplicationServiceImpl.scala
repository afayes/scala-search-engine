package com.ixxus.hastalavista.search.service

import com.ixxus.hastalavista.search.model.{CommandItem, _}
import com.ixxus.hastalavista.search.repository.Repository
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Service

/**
  * todo add comments.
  */
@Service
class ApplicationServiceImpl extends ApplicationService {

  @Autowired
  @Qualifier("pageRepository")
  private val pageRepository:Repository[PageKey, PageCommandItem, PageQueryItem] = null

  @Autowired
  @Qualifier("analyticRepository")
  private val analyticRepository:Repository[AnalyticKey, CommandItem, AnalyticQueryItem] = null;

  override def save(item: CommandItem): Unit = item match {
    case p:PageCommandItem => pageRepository.save(p)
    case a:AnalyticRetrievalDateCommandItem => analyticRepository.save(a)
    case a:AnalyticClickCommandItem => analyticRepository.save(a)
  }

  override def get(url: KeyItem): QueryItem = ???

  override def getAll(): Seq[QueryItem] = ???

  override def search(query: String): Seq[PageQueryItem] = ???
}
