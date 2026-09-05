package com.example.employeemanagementSystem.customizations;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(cors-> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf->csrf.disable())
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST,"/api/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/departments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/departments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/departments/**").hasRole("ADMIN")
                        //only admins can add,update,del employees and departments
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/departments/**").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("ems1-frontend-g7mc42dqa-kjs-3s-projects.vercel.app"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }
}
