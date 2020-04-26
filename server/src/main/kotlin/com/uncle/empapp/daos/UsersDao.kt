package com.uncle.empapp.daos

import com.uncle.empapp.models.daos.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersDao: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
