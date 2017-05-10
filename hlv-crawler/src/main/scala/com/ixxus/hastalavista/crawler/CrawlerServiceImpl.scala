package com.ixxus.hastalavista.crawler

import javax.annotation.PostConstruct

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import scala.collection.mutable
import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * This class represents the a crawler service.
  */
@Service
class CrawlerServiceImpl extends CrawlerService {

  private val logger = LoggerFactory.getLogger(classOf[CrawlerServiceImpl])

  private val enc = "ISO-8859-1"

  @Value("${crawler.baseUrl}")
  private var baseUrl: String = _

  @Value("${crawler.maxPages}")
  private var maxPages: Int = _

  private val linksPattern = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))".r

  private val htmlPattern = "<html.*>.*".r

  private val ignoreExtensions = List("css", "png", "ico")

  @PostConstruct
  def crawl(): Unit = {
    crawl(baseUrl, collection.mutable.Set[String]())
  }

  private def crawl(url: String, urls: mutable.Set[String]): Unit = {
    Future {
      if (urls.size < maxPages && !urls.contains(url)) {
        logger.debug("crawling {} - currently crawled {} urls", url, urls.size)
        try {
          val source = Source.fromURL(url, enc)
          val html = source.mkString

          if (htmlPattern.findFirstIn(html) != None) {
            urls.add(url)
            indexPage(url, html)
            linksPattern.findAllIn(html).matchData.foreach {
              linkFound => {
                var link = linkFound.group(1).replace("\"", "")
                link = if (link.charAt(0) == '/') baseUrl + link else link;
                logger.debug("found url " + link)
                if (!ignoreUrl(link) && link.contains(baseUrl) && !urls.contains(link)) {
                  crawl(link, urls)
                }
              }
            }
          }
        } catch {
          case e: Exception => logger.error("Error occurred", e)
        }
      }
    }
  }

  def indexPage(url: String, html: String):Unit = {
    Future {
      logger.debug("Indexing {}", url)
      // todo call search service
      logger.info("Indexed {}", url)
    }
  }

  def ignoreUrl(url: String): Boolean = {
    ignoreExtensions.find(url.endsWith(_)).nonEmpty
  }
}
