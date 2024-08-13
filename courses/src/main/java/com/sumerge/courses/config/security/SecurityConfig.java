package com.sumerge.courses.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sumerge.courses.config.security.filters.ValidationReportFilter;
import com.sumerge.courses.config.security.userDetails.AdminDetailsService;
import com.sumerge.courses.config.security.userDetails.AuthorDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    
    AdminDetailsService adminDetailsService;

    AuthorDetailsService authorDetailsService;

    @Bean
    SecurityFilterChain securtiyFilterChain(HttpSecurity http) throws Exception
    {
        http 
            .csrf(AbstractHttpConfigurer::disable)// this gave me error always 403 while testing from the terminal with curl command
            .addFilterBefore(new ValidationReportFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests((authorize) -> authorize
                                            .requestMatchers("/courses/add", "courses/update/description/{id}").hasAnyAuthority("AUTHOR_ROLE")
                                            .requestMatchers("/courses/delete/{id}").hasAnyAuthority("ADMIN_ROLE")
                                            .requestMatchers("/author/add").hasAnyAuthority("ADMIN_ROLE")  
                                            .requestMatchers("/courses/all", "/courses/discover", "/swagger-ui/**", "/api-docs/**", "/author/**")
                                            .permitAll()       
                                    )
            .userDetailsService(userDetailsService(adminDetailsService, authorDetailsService))
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean 
    UserDetailsService userDetailsService(AdminDetailsService adminDetailsService, AuthorDetailsService authorDetailsService)
    {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {
                try{
                    return adminDetailsService.loadUserByUsername(username);
                }
                catch(UsernameNotFoundException ex){
                    return authorDetailsService.loadUserByUsername(username);
                }
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
            
        };
    }
}
