package com.ixxus.hastalavista_refactor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import springfox.documentation.builders.PathSelectors.ant
import springfox.documentation.builders.{ApiInfoBuilder, RequestHandlerSelectors}
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
class SearchApplication

object SearchApplication  {

    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[SearchApplication])
    }

    @Bean
    def submissionApi: Docket = submissionApi("com.ixxus.hastalavista_refactor", "Search", "Search api")

    def submissionApi(packageName: String, title: String, description: String): Docket = {
        val apiInfo: ApiInfo = new ApiInfoBuilder().title(title).description(description).build
        new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo)
          .select.apis(RequestHandlerSelectors
          .basePackage(packageName))
          .paths(ant("/**/*")).build
    }
}
