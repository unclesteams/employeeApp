package com.uncle.empapp.models.daos

import javax.persistence.*

@Entity
@Table(name = "employees")
class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Long? = null,
        @Column(name = "email") var email: String,
        @Column(name = "name") var name: String,
        @Column(name = "lastname") var lastname: String,
        @Column(name = "description") var description: String = "") {

    override fun toString(): String {
        return "User(id='$id', email='$email', name='$name', lastname='$lastname', description='$description')"
    }
}