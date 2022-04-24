package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, RSB_AO_VERIFIER;

    @Override
    public String getAuthority() {
        // строковое представление USER
        return name();
    }
}
