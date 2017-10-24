package com.yevhenpanko.pcommerce.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "USER")
public class User extends AbstractEntity {

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLE", nullable = false)
    private UserRole role;

    public User() {
    }
}
