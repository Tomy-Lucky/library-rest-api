package com.example.assignment.Tamir.service

import com.example.assignment.Tamir.exception.ElementNotFoundException
import com.example.assignment.Tamir.model.MemberModel
import com.example.assignment.Tamir.model.toDTO
import com.example.assignment.Tamir.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun findAll() = memberRepository.findAll().map { it.toDTO() }

    fun findById(id: Long) = memberRepository.findByIdOrNull(id)?.toDTO() ?: throw ElementNotFoundException("member")

    fun addMember(name: String) = memberRepository.save(
        MemberModel(
            id = 0,
            name = name
        )
    ).toDTO()

}
