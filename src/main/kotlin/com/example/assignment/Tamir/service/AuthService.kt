package com.example.assignment.Tamir.service

import com.example.assignment.Tamir.web.filters.Role
import com.example.assignment.Tamir.web.jwt.JWTService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JWTService,
    private val memberService: MemberService
) {

    fun authByNameAndPassword(name: String, password: String, role: Role): String {
        memberService.findByNameAndPassword(name = name, password = password)
        return jwtService.sign(role = role)
    }
}
