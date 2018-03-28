package com.yevhenpanko.pcommerce.controller;

import com.yevhenpanko.pcommerce.model.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.security.config.BeanIds.AUTHENTICATION_MANAGER;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(@Qualifier(AUTHENTICATION_MANAGER) AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @ResponseBody
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseValue<LoginStatus> authenticate(@RequestBody UserDetails userDetails) {
        final String email = userDetails.getEmail();
        final String password = userDetails.getPassword();
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                email,
                password
        );

        token.setDetails(userDetails);

        try {
            final Authentication auth = authenticationManager.authenticate(token);
            final SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            return new ResponseValue<>(new LoginStatus(auth.isAuthenticated(), auth.getName()));
        } catch (BadCredentialsException e) {
            return new ResponseValue<>(new LoginStatus(false, null));
        }
    }

    public class LoginStatus {
        private boolean loggedIn;
        private String username;

        public LoginStatus(boolean loggedIn, String username) {
            this.loggedIn = loggedIn;
            this.username = username;
        }

        public boolean isLoggedIn() {
            return loggedIn;
        }

        public String getUsername() {
            return username;
        }
    }
}
