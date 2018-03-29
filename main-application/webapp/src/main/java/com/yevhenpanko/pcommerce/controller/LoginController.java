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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static org.springframework.security.config.BeanIds.AUTHENTICATION_MANAGER;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(@Qualifier(AUTHENTICATION_MANAGER) AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @ResponseBody
    @RequestMapping(value = "/authenticate", method = POST)
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

    @ResponseBody
    @RequestMapping(value = "/logout", method = GET)
    public ResponseValue<LoginStatus> logout(HttpServletRequest request, HttpServletResponse response) {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, authentication);
        }

        return new ResponseValue<>(new LoginStatus(false, null));
    }

    @ResponseBody
    @RequestMapping(value = "/status", method = GET)
    public ResponseValue<LoginStatus> getLoginStatus(Principal principal) {
        if (principal != null) {
            return new ResponseValue<>(new LoginStatus(true, principal.getName()));
        } else {
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
