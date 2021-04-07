package com.example.assignment.Tamir.service

import com.example.assignment.Tamir.dto.Book
import com.example.assignment.Tamir.exception.ElementNotFoundException
import com.example.assignment.Tamir.model.BookAvailabilityModel
import com.example.assignment.Tamir.model.BookModel
import com.example.assignment.Tamir.model.toDTO
import com.example.assignment.Tamir.repository.BookAvailabilityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class BookUpdateOptions(
    val totalAmount: Long? = null,
    val inUseAmount: Long? = null,
    val overdueAmount: Long? = null
)

@Service
class BookAvailabilityService(
    private val bookAvailabilityRepository: BookAvailabilityRepository
) {

    fun findAll() = bookAvailabilityRepository.findAll().map { it.toDTO() }

    fun findById(id: Long) =
        bookAvailabilityRepository.findByIdOrNull(id)?.toDTO() ?: throw ElementNotFoundException("bookAvailability")

    fun findByBookId(bookId: Long) =
        bookAvailabilityRepository.findByBookId(bookId)?.toDTO() ?: throw ElementNotFoundException("bookAvailability")

    fun findByBookTitle(title: String) =
        bookAvailabilityRepository.findByBookTitle(title)?.toDTO() ?: throw ElementNotFoundException("bookAvailability")

    fun addBookDetails(
        title: String,
        author: String,
        description: String,
        totalAmount: Long,
        inUseAmount: Long,
        overdueAmount: Long
    ) = bookAvailabilityRepository.save(
        BookAvailabilityModel(
            id = 0,
            book = BookModel(
                id = 0,
                title = title,
                author = author,
                description = description
            ),
            totalAmount = totalAmount,
            inUseAmount = inUseAmount,
            overdueAmount = overdueAmount
        )
    ).toDTO()

    @Transactional
    fun updateBookAmounts(id: Long, bookUpdateOptions: BookUpdateOptions) {
        val bookAvailabilityModel = bookAvailabilityRepository.findByIdOrNull(id)
            ?: throw ElementNotFoundException("bookAvailabilityModel")

        if (bookUpdateOptions.totalAmount != null)
            bookAvailabilityModel.totalAmount = bookUpdateOptions.totalAmount
        if (bookUpdateOptions.inUseAmount != null)
            bookAvailabilityModel.inUseAmount = bookUpdateOptions.inUseAmount
        if (bookUpdateOptions.overdueAmount != null)
            bookAvailabilityModel.overdueAmount = bookUpdateOptions.overdueAmount
    }
}
