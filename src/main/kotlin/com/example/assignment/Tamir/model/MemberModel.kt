package com.example.assignment.Tamir.model

import com.example.assignment.Tamir.dto.Member
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "member")
class MemberModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @Column
    var name: String,

    @Column
    var password: String,

    @OneToMany(mappedBy = "member")
    var orders: List<OrderModel>? = null
)

fun MemberModel.toDTO() = Member(
    id = id,
    name = name,
    password = password,
    orders = orders?.map { it.toDTO() }
)
