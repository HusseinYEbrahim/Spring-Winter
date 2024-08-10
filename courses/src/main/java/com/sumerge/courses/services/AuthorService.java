package com.sumerge.courses.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumerge.courses.exceptions.AuthorNotFoundException;
import com.sumerge.courses.models.Author;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public Set<Author> getSetOfAuthors(Set<Integer> authorIds) throws AuthorNotFoundException
    {
        Set<Author> courseAuthors = new HashSet<>();
        for(Integer authorId : authorIds)
        {
            courseAuthors.add(getAuthorById(authorId));
        }
        return courseAuthors;
    }

    public Author getAuthorById(Integer id) throws AuthorNotFoundException
    {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent())
            return author.get();
        throw new AuthorNotFoundException("author with id " + id + " is not found");
    }

    public void addCourseToAuthors(Set<Author> authors, Course course)
    {
        for(Author author : authors){
            author.getCourses().add(course);
            authorRepository.save(author);
        }
    }

    public Author addAuthor(Author author)
    {
        Author saved = authorRepository.save(author);
        return saved;
    }

    public List<Author> getAuthorsByEmail(String emailPrefix)
    {
        return authorRepository.findByEmailStartingWith(emailPrefix);
    }
    
}
