package com.memms.melodicle.auth;

import com.memms.melodicle.auth.jwt.AuthEntryPointJWT;
import com.memms.melodicle.auth.jwt.AuthTokenFilter;
import com.memms.melodicle.services.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //Allows for AOP security using @PreAuthorize and @PostAuthorize before methods
public class SecurityConfig  {

    //UserDetailService injection for dao authentication provider
    @Autowired
    private UserDetailServiceImpl userDetailService;

//  Set up bean for exception handling
    @Autowired
    private AuthEntryPointJWT authEntryPointJWT;

//  Set up custom security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf-> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJWT))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
//                                .requestMatchers("/").permitAll()
                                .requestMatchers("/admin/**").fullyAuthenticated()
                                .anyRequest().authenticated()
                );

        httpSecurity.authenticationProvider(daoAuthenticationProvider());
        httpSecurity.addFilterBefore(authJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

//  Set up bcrypt password encoder, otherwise it will use plain text
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //TODO: Update; Custom JWT Token Filter to be used on each request to check if jwt valid?
    @Bean
    public AuthTokenFilter authJWTTokenFilter() {
        return new AuthTokenFilter();
    }

    // Using DAOAutenticationProvider for authenticating using username and password setup in userDetailService
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }
}
