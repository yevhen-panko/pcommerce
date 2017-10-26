package com.yevhenpanko.pcommerce.test;

import com.yevhenpanko.pcommerce.test.springconfig.TestConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(classes = {TestConfig.class})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

}
