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
        configuration.setConfiguration("/configuration.test.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);
        
        List<ClassStructure> packages = configuration.getConfigurationList();
        assertThat(packages.get(1).getIdentifier(), is("GeneratedInstanceVariable"));
        assertThat(packages.get(1).getImportsList().size(), is(0));
    }
    
    @Test
    public void nopackageReferences() throws Exception {
        configuration.setConfiguration("/configuration.test.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);
        
        List<ClassStructure> packages = configuration.getConfigurationList();
        assertThat(packages.get(0).getIdentifier(), is("GeneratedClassStructure"));
        assertThat(packages.get(0).getImportsList().size(), is(3));
        
    }

    @Test
    public void defaultId() throws Exception {
        configuration.setConfiguration("/configuration.test.properties");
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
        configuration.setConfiguration("/configuration.test.properties");
        readConfiguration.execute(configuration);
        readClassStructure.execute(configuration);
        solveReferences.execute(configuration);

        List<ClassStructure> structure = configuration.getConfigurationList();
        List<String> imports = structure.get(0).getImportsList();
        assertThat(imports.get(0), is("java.util.List"));
        assertThat(imports.get(1), is("java.util.ArrayList"));
    }
}
