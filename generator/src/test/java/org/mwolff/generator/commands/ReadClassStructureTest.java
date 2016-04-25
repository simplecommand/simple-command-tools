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
        configuration.setXmlfile("/class.xml");
        readClassStructure.execute(configuration);
        ClassStructure structure = configuration.getConfigurationList().get(0);
        assertThat(structure, notNullValue());
        assertThat(structure.getAuthor(), is("generator 0.0.2"));
    }
}
