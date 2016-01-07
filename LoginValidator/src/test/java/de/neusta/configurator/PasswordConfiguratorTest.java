package de.neusta.configurator;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import de.neusta.examples.passwordvalidator.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PasswordConfiguratorTest {

    @Resource
    PasswordConfigurator passwordConfigurator;

    @Test
    public void loadConfiguration() throws Exception {
        final boolean result = passwordConfigurator.loadConfiguration();
        assertThat(result, CoreMatchers.is(true));
    }

    @Test
    public void loadConfigurationNotExists() throws Exception {
        ReflectionTestUtils.setField(passwordConfigurator, "configfileName", "notExists");
        final boolean result = passwordConfigurator.loadConfiguration();
        assertThat(result, CoreMatchers.is(false));
    }

    @Test
    public void getLength() throws Exception {
        int length = 0;
        ReflectionTestUtils.setField(passwordConfigurator, "configfileName", "/validator.configuration");
        if (passwordConfigurator.loadConfiguration()) {
            length = passwordConfigurator.getPasswordLength();
        }
        assertThat(length, CoreMatchers.is(19));
    }

    @Test
    public void getLengthInvalidConfigFile() throws Exception {
        Integer length = null;
        ReflectionTestUtils.setField(passwordConfigurator, "configuration", null);
        length = passwordConfigurator.getPasswordLength();
        assertThat(length, CoreMatchers.nullValue());
    }
}
