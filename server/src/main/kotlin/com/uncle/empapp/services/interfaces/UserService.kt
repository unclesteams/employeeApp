package com.uncle.empapp.services.interfaces

import com.uncle.empapp.models.daos.Employee

interface UserService {
    fun getUsers(): List<Employee?>?
    fun addUser(employee: Employee): Boolean
    fun updateUser(email: String, employee: Employee): Boolean
    fun deleteUser(user: String): Boolean
    fun getDistinctName()
}