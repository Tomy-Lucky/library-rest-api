package com.example.assignment.Tamir.repository

import com.example.assignment.Tamir.model.BookModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BookModel, Long>
