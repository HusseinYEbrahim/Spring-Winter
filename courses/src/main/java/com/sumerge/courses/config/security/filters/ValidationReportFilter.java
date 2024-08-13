package com.sumerge.courses.config.security.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class ValidationReportFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String validationReportHeader = httpRequest.getHeader("x-validation-report");
        if(validationReportHeader != null && validationReportHeader.equals("true"))
        {
            chain.doFilter(request, response);
        }
    }
    
}
