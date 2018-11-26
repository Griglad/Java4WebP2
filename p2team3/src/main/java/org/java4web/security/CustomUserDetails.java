package org.java4web.security;

import org.java4web.model.CustomUser;
import org.java4web.model.Doctor;
import org.java4web.model.Patient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomUserDetails implements UserDetails {
    private final CustomUser user;

    public CustomUserDetails(CustomUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = getPrivilegedUserRole();
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + userRole));
    }

    @Override
    public String getPassword() { return user.getPassword(); }

    @Override
    public String getUsername() { return user.getUsername(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    private String getPrivilegedUserRole(){
        if(user instanceof Doctor){
            return UserRole.DOCTOR.name();
        }else if(user instanceof Patient){
            return UserRole.PATIENT.name();
        }else{
            return UserRole.ADMIN.name();
        }
    }
}
