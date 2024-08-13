package com.sumerge.courses.config.security.userDetails;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sumerge.courses.models.Admin;
import com.sumerge.courses.repositories.AdminRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        System.out.println("HERE");
        if(!admin.isPresent())
            throw new UsernameNotFoundException("you are not authorized as an admin");
        return admin.get();
    }
    
}
