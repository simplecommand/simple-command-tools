package org.mwolff.generator.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.Configuration;
import org.mwolff.generator.structures.InstanceVariable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class SolveReferencesTest {
    
    @Resource
    ReadConfiguration<Configuration> readConfiguration;

    @Resource
    ReadClassStructure<Configuration> readClassStructure;
    
    @Resource
    SolveReferences<Configuration> solveReferences;

    @Resource
    Configuration configuration;

    @Test
    public void packageReferences() throws Exception {
        configuration.setConfiguration("/configuration.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);
        
        List<ClassStructure> packages = configuration.getConfigurationList();
        assertThat(packages.get(1).getIdentifier(), is("GeneratedInstanceVariable"));
        assertThat(packages.get(1).getImportsList().size(), is(0));
    }
    
    @Test
    public void nopackageReferences() throws Exception {
        configuration.setConfiguration("/configuration.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);
        
        List<ClassStructure> packages = configuration.getConfigurationList();
        assertThat(packages.get(0).getIdentifier(), is("GeneratedClassStructure"));
        assertThat(packages.get(0).getImportsList().size(), is(3));
        
    }

    @Test
    public void defaultId() throws Exception {
        configuration.setConfiguration("/configuration.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);

        List<ClassStructure> structure = configuration.getConfigurationList();
        ClassStructure toExamine = structure.get(0);
        
        boolean result = false;
        for (InstanceVariable variable : toExamine.getInstanceVariableList()) {
            if (variable.getIdentifier().equals("id")) {
                result = true;
            }
        }
        assertThat(result, is(true));
    }
    
    @Test
    public void oneToManyImports() throws Exception {
        configuration.setConfiguration("/configuration.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);

        List<ClassStructure> structure = configuration.getConfigurationList();
        List<String> imports = structure.get(0).getImportsList();
        assertThat(imports.get(0), is("java.util.List"));
        assertThat(imports.get(1), is("java.util.ArrayList"));
    }
}
