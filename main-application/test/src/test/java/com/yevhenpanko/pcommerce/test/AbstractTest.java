package com.yevhenpanko.pcommerce.test;

import com.yevhenpanko.pcommerce.test.springconfig.TestConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static java.lang.System.currentTimeMillis;

@Test
@ContextConfiguration(classes = {TestConfig.class})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    protected String getUniqueName(String prefix) {
        return prefix + "_" + currentTimeMillis();
    }
}
