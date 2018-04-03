package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.yevhenpanko.pcommerce.utility.LocalDateTimeUtils.of;
import static com.yevhenpanko.pcommerce.utility.LocalDateTimeUtils.toDate;

@Component
public class CustomJdbcTokenRepositoryImpl implements PersistentTokenRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomJdbcTokenRepositoryImpl.class);

    @Autowired
    private UserTokenManagement userTokenManagement;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        userTokenManagement.create(
                persistentRememberMeToken.getUsername(),
                persistentRememberMeToken.getSeries(),
                persistentRememberMeToken.getTokenValue(),
                of(persistentRememberMeToken.getDate())
        );
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        userTokenManagement.update(series, tokenValue, of(lastUsed));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        try {
            final Optional<UserTokenDTO> userToken = userTokenManagement.findBySeries(series);
            if (userToken.isPresent()) {
                final UserTokenDTO ut = userToken.get();

                return new PersistentRememberMeToken(
                        ut.getEmail(),
                        ut.getSeries(),
                        ut.getToken(),
                        toDate(ut.getLastUsed())

                );
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            LOGGER.error("Querying token for series '" + series + "' returned more than one value. Series" + " should be unique");
        } catch (DataAccessException e) {
            LOGGER.error("Failed to load token for series " + series, e);
        }

        return null;
    }

    @Override
    public void removeUserTokens(String email) {

    }
}
