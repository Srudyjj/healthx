package com.healthx.security.service;

import com.healthx.model.User;
import com.healthx.security.model.UserDetailsSecurityWrapper;
import com.healthx.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repository.findByUsername(username);
        return user
                .map(UserDetailsSecurityWrapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
