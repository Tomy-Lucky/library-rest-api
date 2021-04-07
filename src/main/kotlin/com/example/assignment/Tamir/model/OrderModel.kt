package com.example.assignment.Tamir.model

import com.example.assignment.Tamir.dto.Order
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "orders")
class OrderModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: MemberModel,

    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: BookModel,

    @Column
    @Enumerated
    var status: OrderStatus,

    @Column
    var overdueDate: LocalDateTime
)

enum class OrderStatus {
    BOOKED, IN_USE, EXPIRED
}

fun OrderModel.toDTO() = Order(
    id = id,
    book = book.toDTO(),
    memberId = member.id,
    status = status,
    overdueDate = overdueDate
)
