package com.yevhenpanko.pcommerce.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@Import({SecurityConfig.class, SpringJPAConfig.class})
@ComponentScan("com.yevhenpanko.pcommerce")
public class SpringWebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/resources/pages/");

        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/index.html").setViewName("index.html");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/login.html").setViewName("login.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(
                        "pages/**",
                        "libs/**",
                        "scripts/**",
                        "css/**"
                )
                .addResourceLocations(
                        "/WEB-INF/resources/pages/",
                        "/WEB-INF/resources/libs/",
                        "/WEB-INF/resources/scripts/",
                        "/WEB-INF/resources/css/"
                );

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
