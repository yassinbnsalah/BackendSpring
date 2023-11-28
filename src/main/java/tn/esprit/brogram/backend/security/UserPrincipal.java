package tn.esprit.brogram.backend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.brogram.backend.DAO.Entities.User;


import java.util.Collection;

@Getter
@Builder
public class UserPrincipal implements UserDetails {
    private final Long userId;
    private final String email;
    private final boolean enabled;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        System.out.println("RRRRRRRRRRRRRRRRRRRRR____________________________________________________");
        return enabled;
    }
}
