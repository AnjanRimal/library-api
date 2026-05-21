package com.anjan.library_api.service;

import com.anjan.library_api.dto.BookDTO;
import com.anjan.library_api.entity.Book;
import com.anjan.library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO createBook(Book book) {
        Book saved = bookRepository.save(book);
        return convertToDTO(saved);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? convertToDTO(book) : null;
    }

    public List<BookDTO> searchBooks(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDTO(Book book) {
        List<String> authorNames = book.getAuthors()
                .stream()
                .map(a -> a.getName())
                .collect(Collectors.toList());
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.isAvailable(),
                authorNames
        );
    }
}