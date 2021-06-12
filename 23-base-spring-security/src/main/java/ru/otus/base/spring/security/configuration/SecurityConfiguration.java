package ru.otus.base.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.otus.base.spring.security.service.LibUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final LibUserDetailsService libUserDetailsService;

    public SecurityConfiguration(LibUserDetailsService libUserDetailsService) {
        this.libUserDetailsService = libUserDetailsService;
    }

    @Override
    public void configure( WebSecurity web ) {
        web.ignoring().antMatchers( "/" );
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers( "/" ).anonymous()
                .and()
                .authorizeRequests().antMatchers( "/book_edit", "/book_add", "/book_delete").authenticated()
                .and()
                .authorizeRequests().antMatchers( "/book_delete").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .passwordParameter("psw")
                .usernameParameter("usr");
    }

    @Override
    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(libUserDetailsService);
    }

}
