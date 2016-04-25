package org.mwolff.generator.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.generator.structures.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class MergeTemplateTest {
    

    @Resource
    MergeTemplate<Configuration> mergeTemplate;
    
    @Resource
    ReadConfiguration<Configuration> readConfiguration;
    
    @Resource
    ReadClassStructure<Configuration> readClassStructure;
    
    @Resource
    SolveReferences<Configuration> solveReferences;

    @Resource
    Configuration configuration;

    @Test
    public void recourcesExists() throws Exception {
        assertThat(mergeTemplate, notNullValue());
    }

    @Test
    public void templateExists() throws Exception {
        final InputStream is = this.getClass().getResourceAsStream("/class-template.vm");
        assertThat(is, notNullValue());
    }

    @Test
    public void mergeTemplateWithConfiguration() throws Exception {
        
        configuration.setConfiguration("/configuration.test.properties");
        readConfiguration.execute(configuration);
        configuration.setXmlfile("/class.xml");
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);
        mergeTemplate.execute(configuration);
        
        String mergeString = configuration.getMergeString();
        assertThat(mergeString, notNullValue());
        File file = new File(configuration.getActualOutputPath() + "/" + configuration.getOutputFile());
        assertThat(configuration.getOutputFile().lastIndexOf(".java") > 0, is(true));
        assertThat(file.exists(), is(true));
        
        File dir = new File(configuration.getActualOutputPath());
        String[]entries = dir.list();
        for(String s: entries){
            File currentFile = new File(dir.getPath(),s);
            currentFile.delete();
        }
        
        dir.delete();
        dir = new File(configuration.getBasepath() + "/org/mwolff");
        dir.delete();
        dir = new File(configuration.getBasepath() + "/org");
        dir.delete();
    }

}
