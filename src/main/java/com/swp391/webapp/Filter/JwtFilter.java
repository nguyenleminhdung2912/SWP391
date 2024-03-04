package com.swp391.webapp.Filter;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Service.AccountService;
import com.swp391.webapp.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AccountService accountService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token= null;
        String userName = null;
        String uri = request.getRequestURI();
        if (uri.contains("/auth/login") || uri.contains("/auth/register")) {
            filterChain.doFilter(request,response);
            return;
        }
        if(authHeader !=null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            userName =jwtService.extractEmail(token);
        }
        if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            AccountEntity accountEntity = accountService.loadUserByUsername(userName);
            if(jwtService.validateToken(token, accountEntity)){
                UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(accountEntity,null, accountEntity.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
