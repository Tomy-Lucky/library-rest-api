package com.example.assignment.Tamir.controller

import com.example.assignment.Tamir.service.AuthService
import com.example.assignment.Tamir.web.filters.Role
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @GetMapping("/by-password")
    fun getBookDetails(
        @RequestParam("name", required = true) name: String,
        @RequestParam("password", required = true) password: String,
        @RequestParam("role", required = true) role: Role
    ) = authService.authByNameAndPassword(
        name = name,
        password = password,
        role = role
    )
}
