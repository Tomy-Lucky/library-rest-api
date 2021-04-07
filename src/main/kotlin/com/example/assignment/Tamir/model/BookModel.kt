package com.example.assignment.Tamir.model

import com.example.assignment.Tamir.dto.Book
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "book")
class BookModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @Column
    var title: String,

    @Column
    var author: String,

    @Column
    var description: String
)

fun BookModel.toDTO() = Book(
    id = id,
    title = title,
    author = author,
    description = description,
)

