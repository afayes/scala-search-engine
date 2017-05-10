package com.ixxus.hastalavista.search

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class SearchApplication

object SearchApplication  {

    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[SearchApplication])
    }

}
