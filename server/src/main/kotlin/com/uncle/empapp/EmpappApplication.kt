package com.uncle.empapp

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.io.File
import java.util.*


@SpringBootApplication
@EnableSwagger2
class EmpappApplication : ApplicationRunner, SpringBootServletInitializer()  {
	
	override fun run(arg0: ApplicationArguments) {
	}

	/**
	 * Called in external TOMCAT only
	 * Allow to optionally configure the app with an external properties file (uses jvm parameter "external.conf.dir")
	 * If such a file is not found, the default application.properties is used
	 */
	override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder {

		// the external.properties file must be placed under ${CATALINA_HOME}/employee_app_conf_files/external.properties
		// the application will log under ${CATALINA_HOME}/employee_app_conf_files/logs/
		val configLocation: String? = System.getProperty("catalina.home") + File.separator.toString() + "employee_app_conf_files"
		val configPath: String? = configLocation + File.separator.toString() + "external.properties"

		logger.warn("Configpath: $configPath")
		logger.warn("Starting to run Spring boot app...")
		if (builder != null && configLocation != null && configLocation.isNotEmpty()) {
			val props = Properties()
			props.setProperty("spring.config.location", configPath) //set the config file to use
			builder.application().setDefaultProperties(props)
		} else {
			logger.warn("No global.appconf.dir property found")
		}

		return super.configure(builder)
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
