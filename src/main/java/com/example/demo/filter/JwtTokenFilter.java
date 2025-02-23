package com.example.demo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

  private final String secret = "mts-secret";

  @Override
  protected void doFilterInternal(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    System.out.println(authHeader);
    log.debug(authHeader);

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      authHeader = authHeader.substring(7);
    }

    filterChain.doFilter(request, response);
    }
}
