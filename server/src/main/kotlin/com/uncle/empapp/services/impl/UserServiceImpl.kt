package com.uncle.empapp.services.impl

import com.uncle.empapp.daos.UsersDao
import com.uncle.empapp.exceptions.UserAlreadyExists
import com.uncle.empapp.exceptions.UserNotFound
import com.uncle.empapp.models.daos.Employee
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

    private fun getUser(email: String): Employee? {
        return repository.findByEmail(email)
    }

    override fun getUsers(): List<Employee?>? {
        return repository.findAll().toList()
    }

    @Throws(UserAlreadyExists::class)
    override fun addUser(employee: Employee): Boolean {
        val oldEmployee: Employee? = getUser(employee.email)
        if (oldEmployee == null) {
            repository.save(employee)
            return true
        } else throw UserAlreadyExists()
    }

    @Throws(UserNotFound::class)
    override fun updateUser(email: String, employee: Employee): Boolean {
        val oldEmployee: Employee? = getUser(email)
        if(oldEmployee != null){
            repository.deleteById(oldEmployee.id!!)
            repository.save(employee)
            return true
        } else
            throw UserNotFound()
    }

    @Throws(UserNotFound::class)
    override fun deleteUser(user: String): Boolean {
        val oldEmployee: Employee? = getUser(user)
        if (oldEmployee != null) {
            repository.deleteById(oldEmployee.id!!)
            return true
        } else
            throw UserNotFound()
    }

    override fun getDistinctName() {
        val list : List<Employee>? = repository.findByOrderByName()
        list!!.forEach {  println("Valore " + it.name ) }
    }

}