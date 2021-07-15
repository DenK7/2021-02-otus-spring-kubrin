package ru.otus.spring.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/h2-console/**");    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .authorizeRequests().antMatchers( "/" ).anonymous()
//                .and()
//                .authorizeRequests().antMatchers( "/**").authenticated()
//                .and()
//                .authorizeRequests().antMatchers( "/book_delete").hasAuthority("ADMIN")
                .authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/logout");
    }

    @Override
    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(libUserDetailsService);
    }

}
