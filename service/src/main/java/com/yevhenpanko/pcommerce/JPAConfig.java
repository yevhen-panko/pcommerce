package com.yevhenpanko.pcommerce;

//import org.springframework.context.annotation.Configuration;

//import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.yevhenpanko.pcommerce.repository")
//@PropertySource("classpath:jpa.properties")
public class JPAConfig {

//    @Autowired
//    private Environment env;

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
//        vendorAdapter.setGenerateDdl(true);

//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.thomasvitale.model");
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());

//        return em;
//    }

//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
//        dataSource.setUrl(env.getProperty("hibernate.connection.url"));
//        dataSource.setUsername(env.getProperty("hibernate.connection.username"));
//        dataSource.setPassword(env.getProperty("hibernate.connection.password"));
//        return dataSource;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);

//        return transactionManager;
//    }

//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        properties.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));

//        return properties;
//    }
}
