package com.rateNUS.backend.security;

import com.rateNUS.backend.security.jwt.JwtTokenVerifier;
import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizeUrlsSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtTokenVerifier jwtTokenVerifier;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .requiresChannel().anyRequest().requiresSecure()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                // comment
                .antMatchers("/comment/hostel/**").permitAll()
                .antMatchers("/comment/stall/**").permitAll()
                .antMatchers("/comment/studyArea/**").permitAll()
                .antMatchers("/comment").authenticated()
                .antMatchers("/comment/**").authenticated()
                // hostel
                .antMatchers("/hostel/**").permitAll()
                .antMatchers("/hostel").permitAll()
                .antMatchers("/hostel/search").permitAll()
                .antMatchers("/hostel/update/**").authenticated()
                .antMatchers("/hostel/delete/**").authenticated()
                // stall
                .antMatchers("/stall/**").permitAll()
                .antMatchers("/stall").permitAll()
                .antMatchers("/stall/search").permitAll()
                .antMatchers("/stall/update/**").authenticated()
                .antMatchers("/stall/delete/**").authenticated()
                // study area
                .antMatchers("/studyarea/**").permitAll()
                .antMatchers("/studyarea").permitAll()
                .antMatchers("/studyarea/search").permitAll()
                .antMatchers("/studyarea/update/**").authenticated()
                .antMatchers("/studyarea/delete/**").authenticated()
                //session expiration
                .antMatchers("/checkLogin").authenticated()
                .anyRequest().permitAll();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(Config.frontendURL);
//                        .allowedMethods("GET", "POST", "DELETE", "OPTIONS");
            }
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
        config.setExposedHeaders(List.of("Authorization"));

        return source;
    }
}
