package com.rateNUS.backend.security;

import static com.rateNUS.backend.security.ApplicationUserRole.ADMIN;
import static com.rateNUS.backend.security.ApplicationUserRole.USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizeUrlsSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/comment")
                .hasAnyRole(USER.name(), ADMIN.name())
                .and()
                .formLogin();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("password"))
                        .roles(USER.name())
                        .build();

        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("password123"))
                        .roles(ADMIN.name())
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
