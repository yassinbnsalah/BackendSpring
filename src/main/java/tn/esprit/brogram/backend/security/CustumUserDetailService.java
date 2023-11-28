package tn.esprit.brogram.backend.security;
import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;
import tn.esprit.brogram.backend.Services.UserService;

import java.util.List;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class CustumUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username);
        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())

                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().toString())))
                .build();
    }


}
