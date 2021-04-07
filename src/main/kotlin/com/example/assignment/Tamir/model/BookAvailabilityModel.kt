package com.example.assignment.Tamir.model

import com.example.assignment.Tamir.dto.BookAvailability
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "book_availability")
class BookAvailabilityModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "book_id")
    var book: BookModel,

    @Column
    var totalAmount: Long,

    @Column
    var inUseAmount: Long,

    @Column
    var overdueAmount: Long
)

fun BookAvailabilityModel.toDTO() = BookAvailability(
    id = id,
    book = book.toDTO(),
    totalAmount = totalAmount,
    inUseAmount = inUseAmount,
    overdueAmount = overdueAmount
)
