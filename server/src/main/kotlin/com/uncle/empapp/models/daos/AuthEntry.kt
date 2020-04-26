package com.uncle.empapp.models.daos

import javax.persistence.*

@Entity
@Table(name = "passwords")
class AuthEntry(
        @Id @GeneratedValue val id:Long,
        @Column(name = "email") val email: String,
        @Column(name = "password") val password: String) {

    override fun toString(): String {
        return "AuthEntry(id=$id, email='$email', password='****************')"
    }
}