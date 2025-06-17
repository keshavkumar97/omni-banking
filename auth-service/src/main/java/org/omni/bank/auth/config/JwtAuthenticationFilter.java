package org.omni.bank.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;
    private CustomUserDetailService userDetailService;

    public JwtAuthenticationFilter(@Autowired JwtTokenProvider tokenProvider,
                                   @Autowired CustomUserDetailService userDetailService) {
        this.tokenProvider = tokenProvider;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
//        System.out.println("reached filter");
        String token = getTokenFromRequest(request);
        if (token != null && tokenProvider.validateToken(token)) {
            String userName = tokenProvider.getUserNameFromToken(token);
            UserDetails userDetails =
                    userDetailService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userName, null,
                            userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().
                    buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }else{
            log.error("No valid token found");
        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return (token != null && token.startsWith("Bearer")) ?
                token.substring(7) : null;
    }
}
