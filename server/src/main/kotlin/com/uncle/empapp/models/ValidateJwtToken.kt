package com.uncle.empapp.models

import java.util.*
import javax.persistence.*

/**
 * Provided as body when validating jwt token
 */
data class ValidateJwtToken(val email: String, val jwt: String)

/**
 * Returned by the isTokenValid API
 */
data class ValidToken(val valid: Boolean)