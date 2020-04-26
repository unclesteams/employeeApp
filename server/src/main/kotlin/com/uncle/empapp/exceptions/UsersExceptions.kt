package com.uncle.empapp.exceptions

class UserNotFound : RuntimeException()

class UserAlreadyExists : RuntimeException()

class WrongCredentials : RuntimeException()