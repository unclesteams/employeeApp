package com.uncle.empapp.services

import com.uncle.empapp.daos.UsersDao
import com.uncle.empapp.exceptions.UserAlreadyExists
import com.uncle.empapp.exceptions.UserNotFound
import com.uncle.empapp.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var repository: UsersDao

    private fun getUser(email: String): User? {
        return repository.findByEmail(email)
    }

    override fun getUsers(): List<User?>? {
        return repository.findAll().toList()
    }

    override fun addUser(user: User): Boolean {
        val oldUser: User? = getUser(user.email)
        if (oldUser == null) {
            repository.save(user)
            return true
        } else throw UserAlreadyExists()
    }

    override fun updateUser(email: String, user: User): Boolean {
        val oldUser: User? = getUser(email)
        if(oldUser != null){
            repository.deleteById(oldUser.id!!)
            repository.save(user)
            return true
        } else
            throw UserNotFound()
    }

    override fun deleteUser(user: String): Boolean {
        val oldUser: User? = getUser(user)
        if (oldUser != null) {
            repository.deleteById(oldUser.id!!)
            return true
        } else
            throw UserNotFound()
    }

}