package de.mwolff.examples.passwordvalidator;

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
    LengthValidator<PasswordParameter> lengthValidator;

    @Resource
    PasswordService passwordService;

    @Resource
    PasswordParameter passwordParameter;

    @Test
    public void lengthValidatorTest() throws Exception {
        assertThat(lengthValidator, CoreMatchers.notNullValue());
    }

    @Test
    public void loginServiceTest() throws Exception {
        assertThat(passwordService, CoreMatchers.notNullValue());
    }

    @Test
    public void passwordParameterTest() throws Exception {
        assertThat(passwordParameter, CoreMatchers.notNullValue());
    }

}
