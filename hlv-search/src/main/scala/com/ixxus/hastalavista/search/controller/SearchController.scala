package com.ixxus.hastalavista.search.controller

import com.ixxus.hastalavista.search.model._
import com.ixxus.hastalavista.search.service.{RepositoryService, RepositoryServiceImpl, SearchServiceProvider, SharedRepositoryServiceProvider}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._
/**
  * todo add comments.
  */
@RestController
@RequestMapping(Array("/search"))
class SearchController extends SharedRepositoryServiceProvider with SearchServiceProvider {

  // todo make configurable through props
  val contentPreviewLength = 10;
  val maxSearchResults = 10

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.POST))
  @ResponseBody
  def index(@RequestBody page: Page):Unit = {
    repositoryService.save(PageCommandItem(page.url, page.content, page.creationDate))
  }

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.GET))
  @ResponseBody
  def index():java.util.List[Page] = {
    repositoryService.getAll(ItemTypes.Page).slice(0, maxSearchResults).map(queryItemToPage).asJava
  }

  @RequestMapping(method=Array(RequestMethod.GET))
  @ResponseBody
  def search(@RequestParam query:String):java.util.List[Page] = {
    searchService.search(query).slice(0, maxSearchResults).map(queryItemToPage).asJava
  }

  def queryItemToPage(q:QueryItem):Page = {
    val p = q.asInstanceOf[PageQueryItem]
    Page(p.url, p.content.substring(0, if (p.content.length < contentPreviewLength) p.content.length else contentPreviewLength), p.creationDate)
  }
}
