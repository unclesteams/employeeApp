package com.uncle.empapp.models

import java.util.*

/**
 * Return user email/username and jwt token with its expiry date
 */
class UserAuthenticated(val email: String, val jwt: String, val expirationDate: Date)