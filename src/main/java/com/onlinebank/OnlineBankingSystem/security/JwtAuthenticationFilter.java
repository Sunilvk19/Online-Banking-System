package com.onlinebank.OnlineBankingSystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    // Constructor injection for JwtUtil
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        if (jwtUtil == null) {
            throw new IllegalArgumentException("JwtUtil must not be null");
        }
        this.jwtUtil = jwtUtil;
    }

    /**
     * Filter that authenticates requests using JWT tokens for the Online Banking System.
     * Extracts username and role from the token and sets authentication in the security context.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String path = request.getServletPath();
        if(path.startsWith("/auth/")){
            filterChain.doFilter(request, response);
            return;
        }
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            try{
                username = jwtUtil.extractUsername(jwt);
            }catch (Exception e){
                logger.error("Error extracting username from JWT", e);
            }
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            try{
                if(jwtUtil.validateToken(jwt,username)){
                    String role = jwtUtil.extractRole(jwt);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+ role))
                            );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }catch (Exception e){
                logger.error("Error validating JWT token", e);
            }
        }
        filterChain.doFilter(request,response);
    }
}
