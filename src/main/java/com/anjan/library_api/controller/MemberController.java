package com.anjan.library_api.controller;

import com.anjan.library_api.entity.Member;
import com.anjan.library_api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/{memberId}/borrow/{bookId}")
    public String borrowBook(@PathVariable Long memberId,
                             @PathVariable Long bookId) {
        return memberService.borrowBook(memberId, bookId);
    }

    @PostMapping("/{memberId}/return/{bookId}")
    public String returnBook(@PathVariable Long memberId,
                             @PathVariable Long bookId) {
        return memberService.returnBook(memberId, bookId);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}