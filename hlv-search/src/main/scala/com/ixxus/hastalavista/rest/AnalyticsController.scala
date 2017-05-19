package com.ixxus.hastalavista.rest

import com.ixxus.hastalavista.analytics.Analytics
import com.ixxus.hastalavista.config.Config
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._

/**
  * todo add comments.
  */
@RestController
@RequestMapping(Array("/analytics"))
class AnalyticsController {

    val components = Config.components

    @RequestMapping(value = Array("/click"), method = Array(RequestMethod.POST))
    def click(@RequestParam url: String) {
        components.analyticsService.updateClickCount(url)
    }

    @RequestMapping(method = Array(RequestMethod.GET))
    @ResponseBody
    def getAnalytics():java.util.List[Analytics] = components.analyticsService.getAll().asJava
}


