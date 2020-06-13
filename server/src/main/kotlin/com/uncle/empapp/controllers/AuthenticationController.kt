package com.uncle.empapp.controllers

import com.uncle.empapp.exceptions.WrongCredentials
import com.uncle.empapp.jwt.JwtTokenUtil
import com.uncle.empapp.models.UserAuthenticated
import com.uncle.empapp.models.ValidToken
import com.uncle.empapp.models.ValidateJwtToken
import com.uncle.empapp.models.daos.AuthEntry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import java.util.*


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

        if (jwtTokenUtil != null && userDetailsService != null) {
            val details: UserDetails = userDetailsService.loadUserByUsername(body.email)
            val token: String = jwtTokenUtil.generateToken(details)
            val expirationDate: Date = jwtTokenUtil.getExpirationDateFromToken(token)
            return UserAuthenticated(body.email, token, expirationDate)
        } else {
            throw Exception("internal error")
        }

    }

    @PostMapping("/isTokenValid")
    fun validateJwt(@RequestBody body: ValidateJwtToken): ValidToken? {
        if (jwtTokenUtil != null && userDetailsService != null) {
            logger.info("Request jwt token validation ${body}")
            try {
                // check this email exists in db (exception is thrown on error)
                userDetailsService.loadUserByUsername(body.email)
                val isValid: Boolean = jwtTokenUtil.validateToken(body.jwt) &&
                        jwtTokenUtil.getUsernameFromToken(body.jwt).equals(body.email)
                return ValidToken(isValid)
            } catch (ex: Throwable) {
                return ValidToken(valid = false)
            }
        } else {
            throw Exception("internal error")
        }
    }
}