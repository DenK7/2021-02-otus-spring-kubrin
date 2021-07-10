package ru.otus.spring.mvc.config;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.otus.spring.mvc.service.LibUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LibUserDetailsService libUserDetailsService;

    public SecurityConfig(LibUserDetailsService libUserDetailsService) {
        this.libUserDetailsService = libUserDetailsService;
    }

    @Override
    public void configure( WebSecurity web ) {
        web.ignoring().antMatchers( "/" );
    }



}
