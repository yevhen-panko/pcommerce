package com.yevhenpanko.pcommerce.entity.user;

import com.yevhenpanko.pcommerce.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TOKEN")
public class UserToken extends AbstractEntity {

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "SERIES", nullable = false)
    private String series;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "LAST_USED", nullable = false)
    private LocalDateTime lastUsed;

    public UserToken(String email, String series, String token, LocalDateTime lastUsed) {
        this.email = email;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public UserToken() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }
}
