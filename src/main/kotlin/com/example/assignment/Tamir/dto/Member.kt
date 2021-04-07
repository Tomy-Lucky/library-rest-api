package com.example.assignment.Tamir.dto

data class Member(
    val id: Long,
    val name: String,
    val orders: List<Order>? = null
)
