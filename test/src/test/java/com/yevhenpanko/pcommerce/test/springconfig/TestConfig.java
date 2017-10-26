package com.yevhenpanko.pcommerce.test.springconfig;

import com.yevhenpanko.pcommerce.springconfig.AbstractApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;

import static org.springframework.orm.jpa.vendor.Database.H2;

@PropertySource("classpath:/jpa.properties")
public class TestConfig extends AbstractApplicationConfig {

    @Autowired
    private Environment env;

    @Override
    protected Database getDataBase() {
        return H2;
    }

    @Override
    protected Environment getEnvironment() {
        return env;
    }
}
