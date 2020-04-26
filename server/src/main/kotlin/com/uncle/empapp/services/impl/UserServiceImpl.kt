package com.uncle.empapp.services.impl

import com.uncle.empapp.daos.UsersDao
import com.uncle.empapp.exceptions.UserAlreadyExists
import com.uncle.empapp.exceptions.UserNotFound
import com.uncle.empapp.models.daos.User
import com.uncle.empapp.services.interfaces.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    // create the logger
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var repository: UsersDao

    private fun getUser(email: String): User? {
        return repository.findByEmail(email)
    }

    override fun getUsers(): List<User?>? {
        return repository.findAll().toList()
    }

    @Throws(UserAlreadyExists::class)
    override fun addUser(user: User): Boolean {
        val oldUser: User? = getUser(user.email)
        if (oldUser == null) {
            repository.save(user)
            return true
        } else throw UserAlreadyExists()
    }

    @Throws(UserNotFound::class)
    override fun updateUser(email: String, user: User): Boolean {
        val oldUser: User? = getUser(email)
        if(oldUser != null){
            repository.deleteById(oldUser.id!!)
            repository.save(user)
            return true
        } else
            throw UserNotFound()
    }

    @Throws(UserNotFound::class)
    override fun deleteUser(user: String): Boolean {
        val oldUser: User? = getUser(user)
        if (oldUser != null) {
            repository.deleteById(oldUser.id!!)
            return true
        } else
            throw UserNotFound()
    }

}