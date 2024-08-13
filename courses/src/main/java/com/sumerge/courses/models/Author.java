package com.sumerge.courses.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sumerge.courses.config.security.authorities.AuthorAuthority;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Author implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;
    
    @Column(
        nullable = false,
        unique = true
    )
    String name;

    @Column(
        nullable = false
    )
    String password;
    
    @Column(
        nullable = false,
        unique = true
    )
    String email;

    LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    Set<Course> courses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new AuthorAuthority());
    }

    @Override
    public String getUsername() {
        return name;
    }


}
