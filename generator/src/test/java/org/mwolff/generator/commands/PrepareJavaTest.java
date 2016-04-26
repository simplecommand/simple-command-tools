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