package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserTokenDTO;
import com.yevhenpanko.pcommerce.entity.user.UserToken;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserTokenManagement {

    Optional<UserTokenDTO> findBySeries(String series);

    @Transactional
    long create(String email, String series, String token, LocalDateTime lastUsed);

    @Transactional
    long update(String series, String token, LocalDateTime lastUsed);

    @Transactional
    void delete(String email);

    default UserTokenDTO toUserTokenDTO(UserToken userToken) {
        return new UserTokenDTO(
                userToken.getId(),
                userToken.getEmail(),
                userToken.getSeries(),
                userToken.getToken(),
                userToken.getLastUsed()
        );
    }
}
