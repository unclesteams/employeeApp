package com.uncle.empapp.controllers

import com.uncle.empapp.exceptions.UserAlreadyExists
import com.uncle.empapp.exceptions.UserNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController{

    @ExceptionHandler(value = [(UserNotFound::class)])
    fun exception(exception: UserNotFound): ResponseEntity<Any?>? {
        return ResponseEntity("User not found", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(UserAlreadyExists::class)])
    fun exception(exception: UserAlreadyExists): ResponseEntity<Any?>? {
        return ResponseEntity("User already exists", HttpStatus.BAD_REQUEST)
    }

}