package com.example.assignment.Tamir.controller

import com.example.assignment.Tamir.model.OrderStatus
import com.example.assignment.Tamir.service.OrderService
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping("/add/order")
    fun createOrder(@RequestBody addOrder: AddOrder) = orderService.addOrder(
        bookId = addOrder.bookId,
        memberId = addOrder.memberId,
        orderStatus = addOrder.orderStatus,
        overdueDate = addOrder.overdueDate
    )

    @DeleteMapping("{id}")
    fun deleteOrderById(@PathVariable id: Long) {
        orderService.deleteById(id)
    }

    @GetMapping("/orders-list")
    fun getAllOrders() = orderService.findAll()

    @GetMapping("/overdue-orders")
    fun getAllOverdueOrders() = orderService.findAllOverdueOrders()

    @GetMapping("/in-use-book-orders")
    fun getAllInUseBookOrders() = orderService.findAllInUseBookOrders()
}

data class AddOrder(
    val bookId: Long,
    val memberId: Long,
    val orderStatus: OrderStatus,
    val overdueDate: LocalDateTime
)
