package ru.otus.spring.mvc.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.mvc.domain.User;
import ru.otus.spring.mvc.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public LibUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + userName);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), "{noop}"+user.getPassword().toLowerCase(), true, true,
                true, true, getAuthorities(user.getRole()));
    }

    //для примера только 1 роль, а не список
    private static List<GrantedAuthority> getAuthorities (String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
