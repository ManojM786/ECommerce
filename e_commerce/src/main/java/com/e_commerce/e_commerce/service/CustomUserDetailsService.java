package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserManagementDAO userManagementDAO;

    public CustomUserDetailsService(UserManagementDAO userManagementDAO) {
        this.userManagementDAO = userManagementDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData user = userManagementDAO.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name());
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassWord())
                .authorities(Collections.singleton(authority))
                .build();
    }
}
