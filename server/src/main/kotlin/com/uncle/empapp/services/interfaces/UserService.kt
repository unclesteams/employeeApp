package com.uncle.empapp.services.interfaces

import com.uncle.empapp.models.daos.User

interface UserService {
    fun getUsers(): List<User?>?
    fun addUser(user: User): Boolean
    fun updateUser(email: String, user: User): Boolean
    fun deleteUser(user: String): Boolean
}