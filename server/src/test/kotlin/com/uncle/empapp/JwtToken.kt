package com.uncle.empapp

import com.uncle.empapp.jwt.JwtTokenUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class JwtToken {

    @Test
    fun testCreateToken() {
        val secret = UUID.randomUUID().toString()
         val utils: JwtTokenUtil = JwtTokenUtil.getJWTUtils(secret)
        val userDetails: UserDetails = User.withUsername("test.test").password("apassword")
                .authorities(listOf()).build()
         val token: String =  utils.generateToken(userDetails)
        assert(token.isNotEmpty())
    }

    @Test
    fun testCreateTokenNotExpired() {
        val secret = UUID.randomUUID().toString()
        val utils: JwtTokenUtil = JwtTokenUtil.getJWTUtils(secret)
        val userDetails: UserDetails = User.withUsername("test.test").password("apassword")
                .authorities(listOf()).build()
        val token: String =  utils.generateToken(userDetails)
        assert(token.isNotEmpty())
        assert(utils.validateToken(token))
    }

    @Test
    fun testCreateTokenWithShortExpiration() {
        val secret: String = UUID.randomUUID().toString()
        val expiresInTenSecs = 10L
        val utils: JwtTokenUtil = JwtTokenUtil.getJWTUtils(secret, expiresInTenSecs)
        val userDetails: UserDetails = User.withUsername("test.test").password("apassword")
                .authorities(listOf()).build()
        val token: String =  utils.generateToken(userDetails)
        assert(token.isNotEmpty())
        assert(utils.validateToken(token))
        Thread.sleep(expiresInTenSecs * 1001)
        assertThrows<io.jsonwebtoken.ExpiredJwtException> {utils.validateToken(token)}
    }

}