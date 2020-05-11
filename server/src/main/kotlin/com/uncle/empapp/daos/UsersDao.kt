package com.uncle.empapp.daos

import com.uncle.empapp.models.daos.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersDao: JpaRepository<Employee, Long> {
    fun findByEmail(email: String): Employee?
    fun findByOrderByName(): List<Employee>?
}