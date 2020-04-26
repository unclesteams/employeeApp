package com.uncle.empapp.controllers

import com.uncle.empapp.exceptions.WrongCredentials
import com.uncle.empapp.jwt.JwtTokenUtil
import com.uncle.empapp.models.UserAuthenticated
import com.uncle.empapp.models.daos.AuthEntry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth/")
@CrossOrigin("*")
class AuthenticationController {

    // create the logger
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val userDetailsService: UserDetailsService? = null

    /**
     * Check the authenticate method in {@link AuthServiceimpl}
     */
    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: WrongCredentials) {
            throw WrongCredentials()
        } catch (e: Throwable) {
            throw WrongCredentials()
        }
    }

    @PostMapping("/validate")
    fun auth(@RequestBody body: AuthEntry): UserAuthenticated? {
        logger.info("Login for user ${body}")
        authenticate(body.email, body.password)

        val details: UserDetails = userDetailsService!!.loadUserByUsername(body.email)
        val token: String = jwtTokenUtil!!.generateToken(details)
        return UserAuthenticated(body.email, token)

    }
}