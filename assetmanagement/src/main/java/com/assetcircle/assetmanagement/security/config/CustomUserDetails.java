package com.assetcircle.assetmanagement.security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final String emailId;

    public CustomUserDetails(String emailId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }
}
