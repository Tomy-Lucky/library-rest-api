package com.example.assignment.Tamir.dto

data class BookAvailability(
    val id: Long,
    val book: Book,
    val totalAmount: Long,
    val inUseAmount: Long,
    val overdueAmount: Long
)
