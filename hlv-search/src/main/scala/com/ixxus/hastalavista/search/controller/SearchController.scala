package com.ixxus.hastalavista.search.controller

import com.ixxus.hastalavista.search.model._
import com.ixxus.hastalavista.search.service.{ApplicationService, ApplicationServiceImpl}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._
/**
  * todo add comments.
  */
@RestController
@RequestMapping(Array("/search"))
class SearchController {

  private val applicationService = new ApplicationServiceImpl

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.POST))
  @ResponseBody
  def index(@RequestBody page: Page):Unit = {
    applicationService.save(PageCommandItem(page.url, page.content, page.creationDate))
  }

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.GET))
  @ResponseBody
  def index():java.util.List[Page] = {
    applicationService.getAll(ItemTypes.Page).map(q => {
      val p = q.asInstanceOf[PageQueryItem]
      Page(p.url, p.content, p.creationDate)
    }).asJava
  }
}
