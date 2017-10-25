package com.yevhenpanko.pcommerce.test;

import com.yevhenpanko.pcommerce.ApplicationConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(classes = {ApplicationConfig.class})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

}
