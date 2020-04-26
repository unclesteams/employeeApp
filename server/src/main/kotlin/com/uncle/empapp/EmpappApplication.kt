package com.uncle.empapp

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@EnableSwagger2
class EmpappApplication : ApplicationRunner, SpringBootServletInitializer()  {

	override fun run(arg0: ApplicationArguments) {
	}

	@Bean
	fun api(): Docket? {
		return Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.uncle.empapp"))
				.build()
	}
}

fun main(args: Array<String>) {
	runApplication<EmpappApplication>(*args)
}
