package com.example.assignment.Tamir.repository

import com.example.assignment.Tamir.model.BookAvailabilityModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookAvailabilityRepository : JpaRepository<BookAvailabilityModel, Long> {

    @Query("""
        from BookAvailabilityModel 
        where book.id = :bookId
    """)
    fun findByBookId(@Param("bookId") bookId: Long): BookAvailabilityModel?

    @Query("""
        from BookAvailabilityModel 
        where book.title = :title
    """)
    fun findByBookTitle(@Param("title") title: String): BookAvailabilityModel?
}
