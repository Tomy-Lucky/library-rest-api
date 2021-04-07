package com.example.assignment.Tamir.dto

import com.example.assignment.Tamir.model.OrderStatus
import java.time.LocalDateTime

data class Order(
    val id: Long,
    val book: Book,
    val memberId: Long,
    val status: OrderStatus,
    val overdueDate: LocalDateTime
)
