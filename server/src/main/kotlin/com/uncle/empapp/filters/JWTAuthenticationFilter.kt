package com.uncle.empapp.filters

import com.uncle.empapp.jwt.JwtTokenUtil
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val requestTokenHeader: String? = request.getHeader("Authorization")
        var username: String? = null
        var jwtToken: String? = null
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)
            try {
                username = jwtTokenUtil!!.getUsernameFromToken(jwtToken)
            } catch (e: IllegalArgumentException) {
                logger.info("Unable to get JWT Token")
            } catch (e: ExpiredJwtException) {
                logger.info("JWT Token has expired")
            } catch (e: Throwable) {
                logger.warn("jwt token is invalid", e)
            }
        } else logger.trace("JWT Token does not begin with Bearer String")
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            // if token is valid configure Spring Security to manually set  authentication
            if (jwtTokenUtil!!.validateToken(jwtToken)) {
                val claims: Claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken)
                logger.debug("Claims are ${claims}")
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                        username, null, listOf())
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                // After setting the Authentication in the context, we specify  that the current user is authenticated. So it passes the Spring Security Configurations successfully.
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        chain.doFilter(request, response)
    }
}