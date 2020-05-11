package com.uncle.empapp.models.daos

import javax.persistence.*

@Entity
@Table(name = "ea_employee")
class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
        @SequenceGenerator(name="employee_generator", sequenceName = "ea_employee_id_seq", allocationSize = 1)
        @Column(name = "id", updatable = false, nullable = false) var id : Long? = null,
        @Column(name = "email") var email: String,
        @Column(name = "name") var name: String,
        @Column(name = "lastname") var lastname: String,
        @Column(name = "description") var description : String? = null) {

    override fun toString(): String {
        return "User(id='$id', email='$email', name='$name', lastname='$lastname', description='$description')"
    }
}