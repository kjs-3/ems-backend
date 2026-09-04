package com.example.employeemanagementSystem.authservice;

import com.example.employeemanagementSystem.customizations.CustomUserDetailsService;
import com.example.employeemanagementSystem.customizations.JwtUtil;
import com.example.employeemanagementSystem.entity.Users;
import com.example.employeemanagementSystem.exceptionHandler.ResourceNotFoundException;
import com.example.employeemanagementSystem.incomingrequests.LoginRequest;
import com.example.employeemanagementSystem.incomingrequests.RegisterRequest;
import com.example.employeemanagementSystem.outgoingresponse.JwtResponse;
import com.example.employeemanagementSystem.userRepository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public void register(@Valid RegisterRequest registerRequest) {
        //since name must be unique
        if(userRepository.existsByuserName(registerRequest.getUserName())){
            throw new ResourceNotFoundException("UserName already Exsists Enter any other Name");
        }
        Users users=new Users();
        users.setUserName(registerRequest.getUserName());
        users.setUserPassword(passwordEncoder.encode(registerRequest.getUserPassword()));
        users.setRole(registerRequest.getRole());
        userRepository.save(users);
    }
    public JwtResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName() ,loginRequest.getUserPassword()));
        UserDetails userDetails=userDetailsService.loadUserByUsername(loginRequest.getUserName());
        String token=jwtUtil.generateToken(userDetails);
        Users users=userRepository.findByuserName(loginRequest.getUserName())
                .orElseThrow();
        return new JwtResponse(users.getUserName(),users.getRole(),token);
    }
}
