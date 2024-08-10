package com.sumerge.courses.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumerge.courses.dto.author.AuthorProfileDTO;
import com.sumerge.courses.dto.author.PostAuthorDTO;
import com.sumerge.courses.exceptions.AuthorNotFoundException;
import com.sumerge.courses.mappers.author.AuthorProfileDTOMapper;
import com.sumerge.courses.mappers.author.PostAuthorDTOMapper;
import com.sumerge.courses.models.Author;
import com.sumerge.courses.services.AuthorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    PostAuthorDTOMapper postAuthorDTOMapper;

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorProfileDTOMapper authorProfileDTOMapper;
    
    @PostMapping("/add")
    public ResponseEntity<Author> add(PostAuthorDTO postAuthorDTO)
    {
        Author author = postAuthorDTOMapper.mapToAuthor(postAuthorDTO);
        Author saved = authorService.addAuthor(author);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getAuthorProfile(@RequestParam Integer id)
    {
        try {
            Author author = authorService.getAuthorById(id);
            AuthorProfileDTO authorProfileDTO = authorProfileDTOMapper.mapToDto(author);
            return ResponseEntity.ok().body(authorProfileDTO);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().body("error occured : " + e.getMessage());
        }
    }

    @GetMapping("/authors-by-email")
    public ResponseEntity<Object> getAuthorProfileWithEmail(@RequestBody String emailPrefix)
    {
        List<Author> authors = authorService.getAuthorsByEmail(emailPrefix);
        List<AuthorProfileDTO> authorProfileDTOs = new ArrayList<>();
        authors.forEach((a) -> {
            authorProfileDTOs.add(authorProfileDTOMapper.mapToDto(a));
        });
        return ResponseEntity.ok().body(authorProfileDTOs);
    }

}
