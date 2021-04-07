package com.example.assignment.Tamir.repository

import com.example.assignment.Tamir.model.MemberModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<MemberModel, Long>
