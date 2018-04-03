package com.yevhenpanko.pcommerce.service.impl;

import com.yevhenpanko.pcommerce.dto.UserTokenDTO;
import com.yevhenpanko.pcommerce.entity.user.UserToken;
import com.yevhenpanko.pcommerce.repository.UserTokenRepository;
import com.yevhenpanko.pcommerce.service.UserTokenManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DefaultUserTokenManagement implements UserTokenManagement {

    private final UserTokenRepository userTokenRepository;

    @Autowired
    public DefaultUserTokenManagement(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public Optional<UserTokenDTO> findBySeries(String series) {
        return userTokenRepository.findBySeries(series).map(this::toUserTokenDTO);
    }

    @Override
    public long create(String email, String series, String token, LocalDateTime lastUsed) {
        final UserToken userToken = new UserToken(
                email,
                series,
                token,
                lastUsed
        );

        return userTokenRepository.save(userToken).getId();
    }

    @Override
    public long update(String series, String token, LocalDateTime lastUsed) {
        final Optional<UserToken> userToken = userTokenRepository.findBySeries(series);

        if (userToken.isPresent()) {
            final UserToken ut = userToken.get();
            ut.setToken(token);
            ut.setLastUsed(lastUsed);

            userTokenRepository.save(ut);

            return ut.getId();
        } else {
            throw new RuntimeException("There are no USER_TOKEN's to update");
        }
    }

    @Override
    public void delete(String email) {
        userTokenRepository.deleteByEmail(email);
    }
}
