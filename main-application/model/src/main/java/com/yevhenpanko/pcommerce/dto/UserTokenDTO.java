package com.yevhenpanko.pcommerce.dto;

import java.time.LocalDateTime;

public class UserTokenDTO {
    private long idl;
    private String email;
    private String series;
    private String token;
    private LocalDateTime lastUsed;

    public UserTokenDTO(long idl, String email, String series, String token, LocalDateTime lastUsed) {
        this.idl = idl;
        this.email = email;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public UserTokenDTO() {
    }

    public long getIdl() {
        return idl;
    }

    public void setIdl(long idl) {
        this.idl = idl;
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

    @Override
    public String toString() {
        return "UserTokenDTO{" +
                "idl=" + idl +
                ", email='" + email + '\'' +
                ", series='" + series + '\'' +
                ", token='" + token + '\'' +
                ", lastUsed=" + lastUsed +
                '}';
    }
}
