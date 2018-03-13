package com.yevhenpanko.pcommerce.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;

import static org.springframework.orm.jpa.vendor.Database.MYSQL;

@PropertySource("file:${user.home}/jpa.properties")
public class ApplicationConfig extends AbstractApplicationConfig {
    private static final String EMPTY_STRING = "";

    private final Environment env;

    @Autowired
    public ApplicationConfig(Environment env) {
        this.env = env;
    }

    @Override
    protected Database getDataBase() {
        return MYSQL;
    }

    @Override
    protected Environment getEnvironment() {
        return env;
    }

    @Override
    protected String getDriverClass() {
        return EMPTY_STRING;
    }
}
