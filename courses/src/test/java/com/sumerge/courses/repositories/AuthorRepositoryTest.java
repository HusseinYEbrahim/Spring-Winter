package com.sumerge.courses.repositories;


import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sumerge.courses.models.Author;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    
    @Test
    public void AuthorRepository_FindByEmailStartingWith_ShouldReturnOneOrMore()
    {
        Author author1 = Author.builder()
                            .email("husseinyasser388@gmail.com")
                            .name("HusseinYasser")
                            .build();
        Author author2 = Author.builder()
                            .email("husseinebrahim2150@gmail.com")
                            .name("HusseinEbrahim")
                            .build();

        Author author3 = Author.builder()
                            .email("haseenMahmoud@gmail.com")
                            .name("Mr Max Payne")
                            .build();
        
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        List<Author> foundAuthors = authorRepository.findByEmailStartingWith("hussein");
        Assertions.assertThat(foundAuthors.size()).isEqualTo(2);
        for(Author author : foundAuthors)
            Assertions.assertThat(author).isNotNull();
    }

    @Test
    public void AuthorRepository_FindByEmailStartingWith_ShouldReturnEmptyList()
    {
        List<Author> foundAuthors = authorRepository.findByEmailStartingWith("what_is_this_email");
        Assertions.assertThat(foundAuthors.size()).isEqualTo(0);
    }
}
