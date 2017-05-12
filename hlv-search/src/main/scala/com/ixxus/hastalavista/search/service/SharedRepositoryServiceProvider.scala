package com.ixxus.hastalavista.search.service

/**
  * todo add comments.
  */
trait SharedRepositoryServiceProvider {
  val repositoryService:RepositoryServiceImpl= singletonRepositoryService
}

object singletonRepositoryService extends RepositoryServiceImpl
