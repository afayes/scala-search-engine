package com.ixxus.hastalavista.crawler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class CrawlerApplication

object CrawlerApplication  {

    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[CrawlerApplication])
    }

}
