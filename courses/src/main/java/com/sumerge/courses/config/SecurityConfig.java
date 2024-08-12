package com.sumerge.courses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sumerge.courses.config.filters.ValidationReportFilter;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securtiyFilterChain(HttpSecurity http) throws Exception
    {
        http.addFilterBefore(new ValidationReportFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }
}
