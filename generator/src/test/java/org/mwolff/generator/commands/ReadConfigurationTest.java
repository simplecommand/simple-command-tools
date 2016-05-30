/**
    Simple Generator Framework.
    Bases on Simple Command Framework

    Framework for easy source code generation via velocity
    @author Manfred Wolff <m.wolff@neusta.de>
    Download: https://github.com/simplecommand/SimpleCommandFramework-Examples


    Copyright (C) 2016 neusta software development

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
    USA
 */
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
        configuration.setConfiguration("src/test/resources/configuration.test.properties");
        readConfiguration.execute(configuration);
        assertThat(configuration.getOutputPath(),
                is("/home/mwolff/windows_shared/mwolffgithub/mwolffgithub/generator/"));
        assertThat(configuration.getPathToTemplate(), is("src/test/resources"));
        assertThat(configuration.getBasepath(), is("/home/mwolff/windows_shared/mwolffgithub/mwolffgithub/generator"));
        assertThat(configuration.getXmlfile(), is("src/test/resources/class.xml"));
        assertThat(configuration.getIdDefault(), is("true"));
        assertThat(configuration.getHibernateSupport(), is("true"));
        assertThat(configuration.getIdType(), is("int"));
    }

    @Test
    public void readInvalidConfiguration() throws Exception {
        thrown.expect(CommandException.class);
        configuration.setConfiguration("/error.properties");
        readConfiguration.execute(configuration);
    }
    
    @Test
    public void readConfigurationWhereIdTypeIsntSet() throws Exception {
        configuration.setConfiguration("src/test/resources/configuration.test.empty.properties");
        readConfiguration.execute(configuration);
        assertThat(configuration.getIdType(), is("int"));
    }
}
