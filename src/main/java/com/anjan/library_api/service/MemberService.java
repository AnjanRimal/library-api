package com.anjan.library_api.service;

import com.anjan.library_api.entity.Book;
import com.anjan.library_api.entity.Member;
import com.anjan.library_api.repository.BookRepository;
import com.anjan.library_api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public String borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (member == null || book == null) return "Member or Book not found";
        if (!book.isAvailable()) return "Book is not available";
        book.setAvailable(false);
        book.setBorrowedBy(member);
        bookRepository.save(book);
        return "Book borrowed successfully";
    }

    public String returnBook(Long memberId, Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) return "Book not found";
        book.setAvailable(true);
        book.setBorrowedBy(null);
        bookRepository.save(book);
        return "Book returned successfully";
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}