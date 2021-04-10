package com.example.assignment.Tamir.controller

import com.example.assignment.Tamir.service.BookAvailabilityService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book-details")
class BookDetailsController(
    private val bookAvailabilityService: BookAvailabilityService
) {

    @GetMapping("/books")
    fun getAllBookDetails() = bookAvailabilityService.findAll()

    @GetMapping("/books/{title}")
    fun getBookDetails(@PathVariable("title") title: String) = bookAvailabilityService.findByBookTitle(title)

    @PostMapping("/add/book-details")
    fun createBook(
       @RequestBody addBookDetails: AddBookDetails
    ) = bookAvailabilityService.addBookDetails(
        title = addBookDetails.title,
        author = addBookDetails.author,
        description = addBookDetails.description,
        totalAmount = addBookDetails.totalAmount,
        inUseAmount = addBookDetails.inUseAmount ?: 0,
        overdueAmount = addBookDetails.overdueAmount ?: 0
    )

    @PutMapping("/replace/book-details")
    fun replaceBook(
        @RequestBody addBookDetails: AddBookDetails
    ) = bookAvailabilityService.replaceBookDetails(
        title = addBookDetails.title,
        author = addBookDetails.author,
        description = addBookDetails.description,
        totalAmount = addBookDetails.totalAmount,
        inUseAmount = addBookDetails.inUseAmount ?: 0,
        overdueAmount = addBookDetails.overdueAmount ?: 0
    )

    @DeleteMapping("/books/{title}")
    fun deleteBook(@PathVariable("title") title: String) {
        bookAvailabilityService.deleteByBookTitle(title)
    }
}

data class AddBookDetails(
    val title: String,
    val author: String,
    val description: String,
    val totalAmount: Long,
    val inUseAmount: Long? = null,
    val overdueAmount: Long? = null
)
