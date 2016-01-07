package de.neusta.configurator;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.neusta.examples.passwordvalidator.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PasswordConfiguratorTest {

    @Resource
    PasswordConfigurator passwordConfigurator;

    @Test
    public void loadConfiguration() throws Exception {
        boolean result = passwordConfigurator.loadConfiguration();
        assertThat(result, CoreMatchers.is(true));
    }

    @Test
    public void getLength() throws Exception {
        int length = 0;
        if (passwordConfigurator.loadConfiguration()) {
            length = passwordConfigurator.getPasswordLength();
        }
        assertThat(length, CoreMatchers.is(19));
    }
}
