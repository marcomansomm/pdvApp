package com.example.pdvapp.security;

import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {
    public JwtAuthFilter(JwtService jwtService, CustomUserDetailService customUserDetailService) {
    }
}
