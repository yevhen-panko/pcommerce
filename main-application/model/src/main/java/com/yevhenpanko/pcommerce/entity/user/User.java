package com.yevhenpanko.pcommerce.entity.user;

import com.yevhenpanko.pcommerce.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    @OneToOne
    @JoinColumn(name = "USER_TOKEN_ID")
    private UserToken token;

    @ManyToOne
    @JoinColumn(name = "USER_ROLE_ID")
    private UserRole role;

    @Column(name = "EMAIL_VERIFIED")
    private boolean emailVerified;

    @Column(name = "SUBSCRIBED")
    private boolean subscribed;

    @Column(name = "PASSWORD_EXPIRATION_TIME")
    private LocalDateTime passwordExpirationTime;

    @Column(name = "FAILED_LOGIN_ATTEMPTS", nullable = false, columnDefinition = "int default 0")
    private int failedLoginAttempts;

    public User(
            String email,
            String password,
            String firstName,
            String lastName,
            UserToken token,
            UserRole role,
            boolean emailVerified,
            boolean subscribed,
            LocalDateTime passwordExpirationTime,
            int failedLoginAttempts
    ) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
        this.role = role;
        this.emailVerified = emailVerified;
        this.subscribed = subscribed;
        this.passwordExpirationTime = passwordExpirationTime;
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserToken getToken() {
        return token;
    }

    public void setToken(UserToken token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public LocalDateTime getPasswordExpirationTime() {
        return passwordExpirationTime;
    }

    public void setPasswordExpirationTime(LocalDateTime passwordExpirationTime) {
        this.passwordExpirationTime = passwordExpirationTime;
    }

    public int isFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", token=" + token +
                ", role=" + role +
                ", emailVerified=" + emailVerified +
                ", subscribed=" + subscribed +
                ", passwordExpirationTime=" + passwordExpirationTime +
                ", failedLoginAttempts=" + failedLoginAttempts +
                '}';
    }
}
