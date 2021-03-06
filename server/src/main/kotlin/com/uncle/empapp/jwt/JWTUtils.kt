package com.uncle.empapp.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap


@Component
class JwtTokenUtil(val providedSec: String? = null, val tokenValidityInterval: Long = 5 * 60 * 60.toLong()) {

    @Value("\${jwt.secret}")
    private val secret: String? = null

    private fun getSecret(): String {
        return if(secret != null) secret else providedSec!!
    }

    //retrieve username from jwt token
    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token, Function { obj: Claims -> obj.subject })
    }

    //retrieve expiration date from jwt token
    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(token, Function { obj: Claims -> obj.expiration })
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    //for retrieveing any information from token we will need the secret key
    fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).body
    }

    //check if the token has expired
    private fun isTokenExpired(token: String?): Boolean {
        val expiration: Date = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    //generate token for user
    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + tokenValidityInterval * 1000))
                .signWith(SignatureAlgorithm.HS512, getSecret()).compact()
    }

    //validate token
    fun validateToken(token: String?): Boolean {
        return !isTokenExpired(token)
    }

    companion object{
        fun getJWTUtils(providedSec: String, tokenValidityInterval: Long = 5 * 60 * 60.toLong()) = JwtTokenUtil(providedSec, tokenValidityInterval)
    }

}