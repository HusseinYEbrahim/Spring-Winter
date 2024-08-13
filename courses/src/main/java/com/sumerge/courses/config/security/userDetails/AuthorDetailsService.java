package com.sumerge.courses.config.security.userDetails;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sumerge.courses.models.Author;
import com.sumerge.courses.repositories.AuthorRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthorDetailsService implements UserDetailsService {

    AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Author> author = authorRepository.findByName(username);
        if(!author.isPresent())
            throw new UsernameNotFoundException("you are not authorized as an author of this course");
        return author.get();
    }
    
}
