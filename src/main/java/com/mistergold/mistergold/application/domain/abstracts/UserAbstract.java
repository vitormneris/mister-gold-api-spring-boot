package com.mistergold.mistergold.application.domain.abstracts;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserAbstract implements UserDetails {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String code;
    protected UserRoleEnum role;
    protected InfoActivation infoActivation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role.equals(UserRoleEnum.ADMINISTRATOR))
            return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"), new SimpleGrantedAuthority("ROLE_CLIENT"));
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
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
        return true;
    }
}
