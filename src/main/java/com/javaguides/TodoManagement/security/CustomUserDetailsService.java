package com.javaguides.TodoManagement.security;

import com.javaguides.TodoManagement.model.User;
import com.javaguides.TodoManagement.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user= userRepository.findByUsernameOrEmail(usernameOrEmail ,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User does  not exists by Username or Email"));
        Set<GrantedAuthority> authorities =user.getRoles().stream()
                .map((role ->  new SimpleGrantedAuthority(role.getName())))
                        .collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}