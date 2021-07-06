package se.lexicon.semester_app.entity;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static se.lexicon.semester_app.entity.Permission.*;


public enum UserType {
    USER(Sets.newHashSet())
    ,ADMIN(Sets.newHashSet(USER_READ,USER_WRITE))
    ,SUPERVISOR(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE,USER_READ,USER_WRITE));


    private final Set<Permission> permissions;

    UserType(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> collect = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        collect.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return collect;
    }
}
