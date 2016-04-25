package org.mwolff.generator.commands;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class PrepareJavaTest {

    @Resource
    PrepareJava<Configuration> prepareJava;

    @Resource
    Configuration configuration;
    
    @Test
    public void resourceExists() throws Exception {
        assertThat(prepareJava, notNullValue());
        assertThat(configuration, notNullValue());
    }
    
    @Test
    public void split() throws Exception {
        String toSplit = "org.mwolff.my.path";
        configuration.setBasepath("/home/mwolff");
        configuration.setOutputPath("/home/mwolff");
        ClassStructure structure = new ClassStructure();
        structure.setIdentifier("Main");
        structure.setPackageString(toSplit);
        configuration.setActualStructure(structure);
        prepareJava.execute(configuration);
        String outputPath = configuration.getActualOutputPath();
        assertThat(outputPath, is("/home/mwolff/org/mwolff/my/path"));
        assertThat(configuration.getOutputFile(), is("Main.java"));
    }
   
}