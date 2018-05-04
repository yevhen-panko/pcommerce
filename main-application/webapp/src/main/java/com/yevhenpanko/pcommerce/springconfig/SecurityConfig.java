package com.yevhenpanko.pcommerce.springconfig;

import com.yevhenpanko.pcommerce.service.CustomPersistentTokenBasedRememberMeServices;
import com.yevhenpanko.pcommerce.service.UserRoleManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static com.yevhenpanko.pcommerce.entity.user.Permission.READ_USER;

@Configuration
@EnableWebSecurity
@ComponentScan("com.yevhenpanko.pcommerce")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserRoleManagement userRoleManagement;
    private final PersistentTokenRepository persistentTokenRepository;

    @Autowired
    public SecurityConfig(
            DataSource dataSource,
            UserRoleManagement userRoleManagement,
            PersistentTokenRepository persistentTokenRepository) {
        this.dataSource = dataSource;
        this.userRoleManagement = userRoleManagement;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        return new CustomPersistentTokenBasedRememberMeServices(
                "pcommerce",
                userDetailsService(),
                persistentTokenRepository
        );
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT EMAIL, PASSWORD, TRUE as ENABLED FROM USER WHERE EMAIL =?")
                .authoritiesByUsernameQuery("SELECT USER.EMAIL, USER_ROLE.NAME FROM USER LEFT JOIN USER_ROLE " +
                        "ON USER.USER_ROLE_ID = USER_ROLE.ID WHERE EMAIL =?")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final SpringURLAccessConfigurer urlAccessConfigurer = new SpringURLAccessConfigurer(http, userRoleManagement);

        urlAccessConfigurer
                .anyRoleWithPermission("/api/user/*", READ_USER)
                .permitAll("/")
                .permitAll("/authenticate")
                .permitAll("/login.html")
                .permitAll("/pages/**")
                .permitAll("/js/**")
                .permitAll("/css/**");

        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices());
    }
}
