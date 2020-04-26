package com.uncle.empapp.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Component
class ConfigureInterceptors : WebMvcConfigurer {
    @Autowired
    var userControllerInterceptor: UserControllerInterceptor? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userControllerInterceptor!!)
    }
}