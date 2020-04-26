package com.uncle.empapp.filters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*

@Component
class SimpleFilter : Filter {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun destroy() {}

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterchain: FilterChain) {
        logger.debug("Remote Host:"+request.getRemoteHost())
        logger.debug("Remote Address:"+request.getRemoteAddr())
        filterchain.doFilter(request, response);
    }

    @Throws(ServletException::class)
    override fun init(filterconfig: FilterConfig?) {
    }
}