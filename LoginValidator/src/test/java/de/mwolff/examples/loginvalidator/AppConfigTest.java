package de.mwolff.examples.loginvalidator;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppConfigTest {

    @Resource
    LengthValidator lengthValidator;

    @Test
    public void lengthValidatorTest() throws Exception {
        assertThat(lengthValidator, CoreMatchers.notNullValue());
    }

}
