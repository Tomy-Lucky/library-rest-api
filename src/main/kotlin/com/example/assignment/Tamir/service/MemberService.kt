package com.example.assignment.Tamir.service

import com.example.assignment.Tamir.dto.Member
import com.example.assignment.Tamir.exception.ElementNotFoundException
import com.example.assignment.Tamir.model.MemberModel
import com.example.assignment.Tamir.model.toDTO
import com.example.assignment.Tamir.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    fun replaceById(id: Long, name: String): Member {
        val memberModel = memberRepository.findByIdOrNull(id)

        return if (memberModel == null)
            addMember(name)
        else {
            memberModel.name = name
            memberModel.toDTO()
        }
    }

    fun deleteMember(name: String) {
        memberRepository.deleteByName(name)
    }

}
