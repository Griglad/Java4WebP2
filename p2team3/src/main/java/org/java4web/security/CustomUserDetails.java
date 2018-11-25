package org.java4web.security;

import org.java4web.model.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomUserDetails implements UserDetails {
    private final CustomUser user;

    private final Map<String, Role> userRolesMap;

    public CustomUserDetails(CustomUser user){
        this.user = user;
        this.userRolesMap = createPrivilegedUserRoles(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = userRolesMap.getOrDefault(user.getUsername(), Role.PATIENT).name();
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

    private Map<String, Role> createPrivilegedUserRoles(CustomUser user){
        HashMap<String, Role> map = new HashMap<>();
        map.put("admin", Role.ADMIN);
        map.put("doctor", Role.DOCTOR);
        return map;
    }
}
