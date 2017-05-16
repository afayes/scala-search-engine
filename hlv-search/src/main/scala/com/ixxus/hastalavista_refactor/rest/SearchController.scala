package com.ixxus.hastalavista_refactor.rest

import java.util.Date

import com.ixxus.hastalavista_refactor.search.{Page, PageIndexComponent, SearchPagesByRelevanceComponent, SearchResultItem}
import org.springframework.web.bind.annotation._
import scala.collection.JavaConverters._

import scala.beans.BeanProperty

/**
  * todo add comments.
  */
@RestController
@RequestMapping(Array("/search"))
class SearchController{

  val components = new SearchPagesByRelevanceComponent with PageIndexComponent {
    override val searchPagesByRelevance = SearchPagesByRelevanceImpl()
    override val pageIndex:PageIndex = PageIndexImpl()
  }

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.POST))
  @ResponseBody
  def index(@RequestBody page: PageRequest):Unit = {
   Page(page.url, page.content, page.creationDate).index()
  }

  @RequestMapping(method=Array(RequestMethod.GET))
  @ResponseBody
  def search(@RequestParam query:String):java.util.List[SearchResultItem] = {
    components.searchPagesByRelevance.search(query).asJava
  }

  class PageRequest() {

    @BeanProperty
    var url: String = _

    @BeanProperty
    var content:String = _

    @BeanProperty
    var creationDate:Date = _
  }
}
