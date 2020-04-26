package com.uncle.empapp.services.impl

import com.uncle.empapp.daos.AuthDao
import com.uncle.empapp.exceptions.WrongCredentials
import com.uncle.empapp.models.daos.AuthEntry
import com.uncle.empapp.services.interfaces.AuthService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthServiceImpl : AuthService {

    // create the logger
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var authDao: AuthDao

    private fun getUserEntryPass(email: String): AuthEntry? {
        return authDao.findByEmail(email)
    }

    /**
     * This must be extended because of the UserDetailsService interface
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("loadUserByUsername is ${username}")
        val entry: AuthEntry? = getUserEntryPass(username!!)
        if(entry!= null){
            return User.withUsername(username).password(entry.password)
                    .authorities(listOf()).build()
        } else
            throw WrongCredentials()
    }

}