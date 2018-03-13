package com.yevhenpanko.pcommerce.entity.user;

import com.yevhenpanko.pcommerce.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends AbstractEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 510)
    private String description;

    @Column(name = "PERMISSION", nullable = false)
    @ElementCollection(targetClass = Permission.class, fetch = EAGER)
    @Enumerated(STRING)
    private List<Permission> permissions;

    public UserRole(String name, Permission... permissions) {
        this.name = name;
        this.permissions = asList(permissions);
    }

    public UserRole() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
