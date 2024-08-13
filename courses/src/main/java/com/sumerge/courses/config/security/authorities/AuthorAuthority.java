package com.sumerge.courses.config.security.authorities;

import org.springframework.security.core.GrantedAuthority;

public class AuthorAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "AUTHOR_ROLE";
    }
    
}
