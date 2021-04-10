package com.example.assignment.Tamir.repository

import com.example.assignment.Tamir.model.OrderModel
import java.time.LocalDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<OrderModel, Long> {

    @Query("""
        from OrderModel 
        where overdueDate < :currentTime
    """)
    fun findAllOverdueOrders(
        @Param("currentTime") currentTime: LocalDateTime
    ) : List<OrderModel>

    @Query("""
        from OrderModel 
        where overdueDate > :currentTime
    """)
    fun findAllInUseBookOrders(
        @Param("currentTime") currentTime: LocalDateTime
    ) : List<OrderModel>

}
