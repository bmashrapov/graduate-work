package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entities.UserEntity;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class UserSecurity implements UserDetails {

    private final UserEntity entity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return entity.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return entity.getPassword();
    }

    @Override
    public String getUsername() {
        return entity.getUserName();
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
        return true;
    }
}

