package com.ixxus.hastalavista.search.controller

import com.ixxus.hastalavista.search.model._
import com.ixxus.hastalavista.search.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody, RestController}
/**
  * todo add comments.
  */
@RestController
@RequestMapping(Array("/search"))
class SearchController {

  @Autowired
  private val applicationService: ApplicationService = null

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.POST))
  @ResponseBody
  def index(page: Page):Unit = {
    applicationService.save(PageCommandItem(page.url, page.content, page.creationDate))
  }

  @RequestMapping(value = Array("/index"), method=Array(RequestMethod.GET))
  @ResponseBody
  def index():Seq[QueryItem] = {
    applicationService.getAll(ItemTypes.Page)
  }
}
