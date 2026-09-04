package com.example.employeemanagementSystem.customizations;

import com.example.employeemanagementSystem.entity.Users;
import com.example.employeemanagementSystem.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepository.findByuserName(username)
                .orElseThrow(
                        ()->new UsernameNotFoundException("UserName doesn't Exsists"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(users.getUserName())
                .password(users.getUserPassword())
                .authorities(List.of(
                        new SimpleGrantedAuthority("ROLE_"+ users.getRole().name())))
                .build();
    }
}
