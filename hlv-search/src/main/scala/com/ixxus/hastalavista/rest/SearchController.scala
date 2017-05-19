package com.ixxus.hastalavista.rest

import java.util.Date

import com.ixxus.hastalavista.config.Config
import com.ixxus.hastalavista.search._
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._
import scala.beans.BeanProperty

/**
  * todo add comments.
  */
@RestController
@RequestMapping(Array("/search"))
class SearchController {

    val components = Config.components

    @RequestMapping(value = Array("/index"), method = Array(RequestMethod.POST))
    def index(@RequestBody page: PageRequest) { components.pageIndex.update(page.url, Page(page.url, page.content, page.creationDate)) }

    @RequestMapping(method = Array(RequestMethod.GET))
    @ResponseBody
    def search(@RequestParam query: String): java.util.List[SearchResultItem] = components.searchPagesByRelevance.search(query).asJava

    @RequestMapping(value = Array("/byLastRetrievalDate"), method = Array(RequestMethod.GET))
    @ResponseBody
    def search(): java.util.List[SearchResultItem] = components.retrievePagesByRetrievalDate.getPages().asJava

}

class PageRequest() {

    @BeanProperty
    var url: String = _

    @BeanProperty
    var content: String = _

    @BeanProperty
    var creationDate: Date = _
}
