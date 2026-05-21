package com.anjan.library_api.service;

import com.anjan.library_api.dto.AuthorDTO;
import com.anjan.library_api.entity.Author;
import com.anjan.library_api.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDTO createAuthor(Author author) {
        Author saved = authorRepository.save(author);
        return convertToDTO(saved);
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        return author != null ? convertToDTO(author) : null;
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private AuthorDTO convertToDTO(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getBio()
        );
    }
}