package com.hlidevs.store_products.security;

import com.hlidevs.store_products.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));

        return authorities;
    }

    @Override
    public String getPassword() {return user.getPassword();
    }

    @Override
    public String getUsername() {return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {return true;
    }

    @Override
    public boolean isAccountNonLocked() {return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {return true;
    }

    @Override
    public boolean isEnabled() {return true;
    }
}
