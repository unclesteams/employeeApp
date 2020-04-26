package com.uncle.empapp.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class UserControllerInterceptor : HandlerInterceptor {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Throws(Exception::class)
    override fun preHandle(
            request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.debug("Pre Handle method is Calling")
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
            request: HttpServletRequest, response: HttpServletResponse, handler: Any,
            modelAndView: ModelAndView?) {
        logger.debug("Post Handle method is Calling")

    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse,
                                 handler: Any, exception: Exception?) {

        logger.debug("Request and Response completed")

    }
}