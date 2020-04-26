package com.uncle.empapp.controllers

import com.uncle.empapp.exceptions.UserAlreadyExists
import com.uncle.empapp.exceptions.UserNotFound
import com.uncle.empapp.models.daos.User
import com.uncle.empapp.services.interfaces.UserService
import org.hibernate.Session
import org.hibernate.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users/")
@CrossOrigin("*")
class UserController {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val session: Session? = null

    // create the logger
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    fun getUsers(@RequestParam(value = "name", required = false) name: String?): List<User?>? {
        return userService?.getUsers() ?: listOf()
    }

    @GetMapping("/add")
    fun addUser() {
        val user: User = User(id = null, email="mail@mail.com", name = "Daniele", lastname =  "Battista")
        if(session != null) {
            val transaction: Transaction? = session.beginTransaction()
            if(transaction != null){
                try{
                    session.save(user)
                    transaction.commit()
                } catch(ex: Exception) {
                       logger.error("Exception ", ex)
                        transaction.rollback()
                } finally {
                    if(session.isOpen)
                     session.close()
                }
            }
        }
    }

    @PostMapping("/")
    @Throws(UserAlreadyExists::class)
    fun createUser(@RequestBody body: User): User? {
        logger.info("Called create user with body ${body}")
        val added: Boolean? = userService?.addUser(body)
        if (added == true) {
            return body
        } else
            return null
    }

    @PutMapping("/{email}")
    @Throws(UserNotFound::class)
    fun updateUser(@PathVariable("email") email: String, @RequestBody body: User): Boolean {
        return userService?.updateUser(email, body) ?: false
    }

    @DeleteMapping("/{email}")
    @Throws(UserNotFound::class)
    fun deleteUser(@PathVariable("email") email: String): Boolean {
        return userService?.deleteUser(email) ?: false
    }
}
