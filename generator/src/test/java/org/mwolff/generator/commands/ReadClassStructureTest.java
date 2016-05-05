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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.mwolff.commons.command.iface.CommandException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class ReadClassStructureTest {

    @Resource
    ReadClassStructure<Configuration> readClassStructure;
    
    @Resource
    Configuration configuration;
    
     @Rule
     public ExpectedException thrown = ExpectedException.none();

    @Test
    public void resourcesExists() throws Exception {
        assertThat(readClassStructure, notNullValue());
    }
    
    @Test
    public void classStructureNotExists() throws Exception {
        thrown.expect(CommandException.class);
        configuration.setXmlfile("/classes.xml");
        readClassStructure.execute(configuration);
    }
    
    @Test
    public void classStructureExists() throws Exception {
        configuration.setXmlfile("src/test/resources/class.xml");
        readClassStructure.execute(configuration);
        ClassStructure structure = configuration.getConfigurationList().get(0);
        assertThat(structure, notNullValue());
        assertThat(structure.getAuthor(), is("generator 0.0.2"));
    }
}
