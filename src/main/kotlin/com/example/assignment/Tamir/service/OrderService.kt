package com.example.assignment.Tamir.service

import com.example.assignment.Tamir.dto.Order
import com.example.assignment.Tamir.exception.ElementNotFoundException
import com.example.assignment.Tamir.model.BookModel
import com.example.assignment.Tamir.model.MemberModel
import com.example.assignment.Tamir.model.OrderModel
import com.example.assignment.Tamir.model.OrderStatus
import com.example.assignment.Tamir.model.toDTO
import com.example.assignment.Tamir.repository.OrderRepository
import java.time.LocalDateTime
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val bookService: BookService,
    private val bookAvailabilityService: BookAvailabilityService,
    private val memberService: MemberService,
    private val orderRepository: OrderRepository
) {

    fun findAll() = orderRepository.findAll().map { it.toDTO() }

    fun findAllOverdueOrders() =
        orderRepository.findAllOverdueOrders(currentTime = LocalDateTime.now()).map { it.toDTO() }

    fun findAllInUseBookOrders() =
        orderRepository.findAllInUseBookOrders(currentTime = LocalDateTime.now()).map { it.toDTO() }

    fun findById(id: Long) = orderRepository.findByIdOrNull(id)?.toDTO() ?: throw ElementNotFoundException("order")

    fun deleteById(id: Long) {
        orderRepository.deleteById(id)
    }

    fun addOrder(
        bookId: Long,
        memberId: Long,
        orderStatus: OrderStatus,
        overdueDate: LocalDateTime
    ): Order {
        val bookAvailability = bookAvailabilityService.findByBookId(bookId)
        val member = memberService.findById(memberId)

        bookAvailabilityService.updateBookAmounts(
            id = bookId,
            bookUpdateOptions = BookUpdateOptions(
                inUseAmount = bookAvailability.inUseAmount + 1
            )
        )

        return orderRepository.save(
            OrderModel(
                id = 0,
                book = BookModel(
                    id = bookAvailability.book.id,
                    title = bookAvailability.book.title,
                    author = bookAvailability.book.author,
                    description = bookAvailability.book.description
                ),
                member = MemberModel(
                    id = member.id,
                    name = member.name
                ),
                status = orderStatus,
                overdueDate = overdueDate
            )
        ).toDTO()
    }
}
