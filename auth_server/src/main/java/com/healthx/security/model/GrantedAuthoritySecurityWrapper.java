package com.healthx.security.model;

import com.healthx.model.Authority;
import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthoritySecurityWrapper implements GrantedAuthority {

    private final Authority authority;

    public GrantedAuthoritySecurityWrapper(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
