package com.uncle.empapp.daos

import com.uncle.empapp.models.daos.AuthEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthDao: JpaRepository<AuthEntry, Long> {
    fun findByEmail(email: String): AuthEntry?
}