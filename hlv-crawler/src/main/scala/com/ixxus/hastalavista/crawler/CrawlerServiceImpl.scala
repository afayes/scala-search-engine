package com.ixxus.hastalavista.crawler

import java.net.URI
import java.util.Date

import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate

import scala.collection.mutable
import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

/**
  * This class represents a crawler service.
  */
object CrawlerService extends App {

    private val logger = LoggerFactory.getLogger(CrawlerService.getClass)

    private val restTemplate = new RestTemplate

    private val enc = "ISO-8859-1"

    private val url = "https://en.wikipedia.org/wiki/Main_Page"

    private var baseUrl: String = "https://en.wikipedia.org"

    private var maxPages: Int = 100

    private var searchServiceIndexUrl: String = "http://localhost:8080/search/index"

    private val linksPattern = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))".r

    private val htmlPattern = "<html.*>.*".r

    private val ignoreExtensions = List("css", "png", "ico")

    def crawl(): Unit = {
        crawl(url, baseUrl, collection.mutable.Set[String]())
    }

    private def crawl(url: String, baseUrl: String, urls: mutable.Set[String], startTime: Long = System.nanoTime()): Unit = {
        Future {
            if (urls.size < maxPages && !urls.contains(url)) {
                logger.debug("crawling {} - currently crawled {} urls", url, urls.size)
                val source = Try(Source.fromURL(url, enc))
                val html = source.flatMap(s => Try(s.mkString))
                val htmlText = html.getOrElse("Could not get source")
                if (htmlPattern.findFirstIn(htmlText) != None) {
                    synchronized(urls) {
                        if (urls.size < maxPages && !urls.contains(url)) {
                            urls.add(url)
                            indexPage(url, htmlText)
                        }
                        ""
                    }

                    linksPattern.findAllIn(htmlText).matchData.foreach {
                        linkFound => {
                            var link = linkFound.group(1).replace("\"", "")
                            link = if (link.charAt(0) == '/') baseUrl + link else link;
                            logger.debug("found url " + link)
                            if (!ignoreUrl(link) && link.contains(baseUrl) && !urls.contains(link)) {
                                crawl(link, baseUrl, urls, startTime)
                            }
                        }
                    }
                }
            } else if (urls.size >= maxPages) {
                val endTime: Long = System.nanoTime();
                val duration: Double = (endTime - startTime) / 1000000000.asInstanceOf[Double]
                println(s"Crawled ${urls.size} urls in $duration seconds")
            }
        }
    }

    def indexPage(url: String, html: String) {
        logger.debug("Indexing {}", url)
        restTemplate.postForEntity(new URI(searchServiceIndexUrl), Page(url, html, new Date()), null);
        logger.info("Indexed {}", url)
    }

    def ignoreUrl(url: String): Boolean = {
        ignoreExtensions.find(url.endsWith(_)).nonEmpty
    }

    crawl()
}
