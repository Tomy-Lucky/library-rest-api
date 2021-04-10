package com.example.assignment.Tamir.controller

import com.example.assignment.Tamir.service.MemberService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/members")
    fun getAllMembers() = memberService.findAll()

    @PostMapping("/add/member")
    fun createMember(@RequestBody addMember: AddMember) = memberService.addMember(name = addMember.name)

    @PutMapping("/replace/member/{id}")
    fun replace(@RequestBody addMember: AddMember, @PathVariable id: Long) =
        memberService.replaceById(id = id, name = addMember.name)

    @DeleteMapping("/add/member/{name}")
    fun deleteMember(@PathVariable name: String) {
        memberService.deleteMember(name = name)
    }
}

data class AddMember(
    val name: String
)


