package com.example.assignment.Tamir.controller

import com.example.assignment.Tamir.model.OrderStatus
import com.example.assignment.Tamir.service.BookAvailabilityService
import com.example.assignment.Tamir.service.MemberService
import com.example.assignment.Tamir.service.OrderService
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/library")
class LibraryController(
    private val memberService: MemberService,
    private val orderService: OrderService,
    private val bookAvailabilityService: BookAvailabilityService
) {

    @GetMapping("/add/book-details")
    fun createBook(
        @RequestParam("title", required = true) title: String,
        @RequestParam("author", required = true) author: String,
        @RequestParam("description", required = true) description: String,
        @RequestParam("totalAmount", required = true) totalAmount: Long,
        @RequestParam("inUseAmount", required = false) inUseAmount: Long? = null,
        @RequestParam("overdueAmount", required = false) overdueAmount: Long? = null,
    ) = bookAvailabilityService.addBookDetails(
        title = title,
        author = author,
        description = description,
        totalAmount = totalAmount,
        inUseAmount = inUseAmount ?: 0,
        overdueAmount = overdueAmount ?: 0
    )

    @GetMapping("/add/member")
    fun createMember(
        @RequestParam("name", required = true) name: String,
        @RequestParam("password", required = true) password: String
    ) = memberService.addMember(name = name, password = password)

    @GetMapping("/add/order")
    fun createOrder(
        @RequestParam("bookId", required = true) bookId: Long,
        @RequestParam("memberId", required = true) memberId: Long,
        @RequestParam("orderStatus", required = true) orderStatus: OrderStatus,
        @RequestParam("name", required = true) overdueDate: LocalDateTime
    ) = orderService.addOrder(
        bookId = bookId,
        memberId = memberId,
        orderStatus = orderStatus,
        overdueDate = overdueDate
    )

    @GetMapping("/orders")
    fun getAllOrders() = orderService.findAll()

    @GetMapping("/overdue-orders")
    fun getAllOverdueOrders() = orderService.findAllOverdueOrders()

    @GetMapping("/in-use-book-orders")
    fun getAllInUseBookOrders() = orderService.findAllInUseBookOrders()

    @GetMapping("/books")
    fun getAllBookDetails() = bookAvailabilityService.findAll()

    @GetMapping("/books/{title}")
    fun getAllBookDetails(
        @PathVariable("title") title: String
    ) = bookAvailabilityService.findByBookTitle(title)

    @GetMapping("/members")
    fun getAllMembers() = memberService.findAll()
}
