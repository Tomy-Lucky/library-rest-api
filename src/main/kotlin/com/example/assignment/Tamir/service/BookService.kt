package com.example.assignment.Tamir.service

import com.example.assignment.Tamir.exception.ElementNotFoundException
import com.example.assignment.Tamir.model.toDTO
import com.example.assignment.Tamir.repository.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun findAll() = bookRepository.findAll().map { it.toDTO() }

    fun findById(id: Long) = bookRepository.findByIdOrNull(id)?.toDTO() ?: throw ElementNotFoundException("book")
}
