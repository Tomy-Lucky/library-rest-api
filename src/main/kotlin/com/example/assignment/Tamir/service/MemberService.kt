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

    fun findByNameAndPassword(name: String, password: String) = memberRepository.findByNameAndPassword(
        name = name,
        password = password
    )?.toDTO() ?: throw ElementNotFoundException("member")

    fun addMember(name: String, password: String) = memberRepository.save(
        MemberModel(
            id = 0,
            name = name,
            password = password
        )
    ).toDTO()

    @Transactional
    fun replaceById(id: Long, name: String, password: String): Member {
        val memberModel = memberRepository.findByIdOrNull(id)

        return if (memberModel == null)
            addMember(name = name, password = password)
        else {
            memberModel.name = name
            memberModel.password = password
            memberModel.toDTO()
        }
    }

    fun deleteMember(name: String) {
        memberRepository.deleteByName(name)
    }

}
