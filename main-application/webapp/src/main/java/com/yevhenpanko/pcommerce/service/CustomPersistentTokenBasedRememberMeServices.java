package com.yevhenpanko.pcommerce.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomPersistentTokenBasedRememberMeServices extends PersistentTokenBasedRememberMeServices {

    public CustomPersistentTokenBasedRememberMeServices(
            String key,
            UserDetailsService userDetailsService,
            PersistentTokenRepository tokenRepository
    ) {
        super(key, userDetailsService, tokenRepository);
    }

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        super.onLoginSuccess(request, response, successfulAuthentication);
    }
}
