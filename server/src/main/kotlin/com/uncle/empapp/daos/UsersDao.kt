package com.uncle.empapp.daos

import com.uncle.empapp.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersDao: CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}