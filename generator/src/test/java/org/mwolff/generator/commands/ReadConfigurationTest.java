package org.mwolff.generator.commands;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mwolff.generator.structures.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class ReadConfigurationTest {

    @Resource
    ReadConfiguration<Configuration> readConfiguration;

    @Resource
    Configuration configuration;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void configurationExists() throws Exception {
        assertThat(readConfiguration, notNullValue());
        assertThat(configuration, notNullValue());
        assertThat(readConfiguration, CoreMatchers.instanceOf(AbstractDefaultChainCommand.class));
    }

    @Test
    public void readConfiguration() throws Exception {
        configuration.setConfiguration("/configuration.properties");
        readConfiguration.execute(configuration);
        assertThat(configuration.getOutputPath(),
                is("/home/mwolff/windows_shared/mwolffgithub/mwolffgithub/generator/"));
        assertThat(configuration.getClassTemplate(), is("class-template.vm"));
        assertThat(configuration.getBasepath(), is("/home/mwolff/windows_shared/mwolffgithub/mwolffgithub/generator"));
        assertThat(configuration.getXmlfile(), is("/class.xml"));
        assertThat(configuration.getIdDefault(), is("true"));
        assertThat(configuration.getHibernateSupport(), is("true"));
    }

    @Test
    public void readInvalidConfiguration() throws Exception {
        thrown.expect(CommandException.class);
        configuration.setConfiguration("/error.properties");
        readConfiguration.execute(configuration);
    }
}
