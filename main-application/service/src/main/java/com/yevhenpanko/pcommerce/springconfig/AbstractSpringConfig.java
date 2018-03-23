package com.yevhenpanko.pcommerce.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static com.yevhenpanko.pcommerce.springconfig.AbstractSpringConfig.PACKAGE_TO_SCAN;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = PACKAGE_TO_SCAN + ".repository")
@ComponentScan(PACKAGE_TO_SCAN)
public abstract class AbstractSpringConfig {
    public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String DIALECT = "hibernate.dialect";
    public static final String SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    public static final String CONNECTION_URL = "hibernate.connection.url";
    public static final String USERNAME = "hibernate.connection.username";
    public static final String PASSWORD = "hibernate.connection.password";
    public static final String SHOW_SQL = "hibernate.show_sql";
    public static final String FORMAT_SQL = "hibernate.format_sql";
    public static final String DRIVER_CLASS = "hibernate.connection.driver_class";
    public static final String PACKAGE_TO_SCAN = "com.yevhenpanko.pcommerce";

    protected abstract Database getDataBase();

    protected abstract Environment getEnvironment();

    protected abstract String getDriverClass();

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(getDataBase());
        vendorAdapter.setGenerateDdl(true);

        final LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan(PACKAGE_TO_SCAN);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    @Bean
    public DataSource getDataSource() {
        final Environment env = getEnvironment();

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getDriverClass());
        dataSource.setUrl(env.getProperty(CONNECTION_URL));
        dataSource.setUsername(env.getProperty(USERNAME));
        dataSource.setPassword(env.getProperty(PASSWORD));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Properties getAdditionalProperties() {
        final Environment env = getEnvironment();

        final Properties properties = new Properties();
        properties.setProperty(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
        properties.setProperty(DIALECT, env.getProperty(DIALECT));
        properties.setProperty(SESSION_CONTEXT_CLASS, env.getProperty(SESSION_CONTEXT_CLASS));
        properties.setProperty(SHOW_SQL, env.getProperty(SHOW_SQL));
        properties.setProperty(FORMAT_SQL, env.getProperty(FORMAT_SQL));

        return properties;
    }
}
