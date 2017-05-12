package com.ixxus.hastalavista.search.service

/**
  * todo add comments.
  */
trait SearchServiceProvider {
  val searchService:SearchService= singletonSearchService
}

object singletonSearchService extends SearchServiceImpl
