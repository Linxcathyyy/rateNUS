package com.rateNUS.backend.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtTokenVerifier extends OncePerRequestFilter {
    public static final Logger logger = Logger.getLogger(JwtTokenVerifier.class.getName());
    @Value("${ratenus.app.jwt.jwtSecret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

            String username = body.getSubject();

            var authorities = (List<Map<String, String>>) body.get("authorities");
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(authMap -> new SimpleGrantedAuthority(authMap.get("authority")))
                    .collect(Collectors.toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.setHeader(
                    "Access-Control-Allow-Headers",
                    "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, "
                            + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            logger.log(Level.WARNING, String.format("token %s cannot be trusted: %s", token, e.getClass().getName()));
            if (e instanceof ExpiredJwtException) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Login Timeout");
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value(), "invalid token");
            }
        }
    }
}
