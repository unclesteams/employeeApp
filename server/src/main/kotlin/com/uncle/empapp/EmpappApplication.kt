package com.uncle.empapp

import com.uncle.empapp.configuration.DatabaseConfiguration
import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.beans.factory.annotation.Value
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
@EnableAdminServer
@EnableSwagger2
class EmpappApplication : ApplicationRunner, SpringBootServletInitializer()  {

	@Value(value = "\${spring.application.name}")
	private val name: String? = null

	override fun run(arg0: ApplicationArguments) {
		if(logger != null)
			logger.info("Hello World from Application Runner!!!!!!!!!, read param $name");
		else
			println("Hello World from Application Runner!!!!!!!!!, read param $name");
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
