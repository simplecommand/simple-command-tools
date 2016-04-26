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
package org.mwolff.generator.structures;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.generator.commands.FileWriter;
import org.mwolff.generator.commands.MergeTemplate;
import org.mwolff.generator.commands.PrepareJava;
import org.mwolff.generator.commands.ReadClassStructure;
import org.mwolff.generator.commands.ReadConfiguration;
import org.mwolff.generator.commands.SolveReferences;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class GeneratorAppConfigTest {

    
    @Resource
    ReadConfiguration<Configuration> readConfiguration;    
    @Resource
    ReadClassStructure<Configuration> readClassStructure;
    
    @Resource
    MergeTemplate<Configuration> mergeTemplate;
    
    @Resource
    FileWriter<Configuration> fileWriter;
    
    @Resource
    PrepareJava<Configuration> prepareJava;
    
    @Resource
    SolveReferences<Configuration> solveReferences;
    
    @Test
    public void notNull() throws Exception {
        assertThat(readConfiguration, notNullValue());
        assertThat(readClassStructure, notNullValue());
        assertThat(mergeTemplate, notNullValue());
        assertThat(fileWriter, notNullValue());
        assertThat(prepareJava, notNullValue());
        assertThat(solveReferences, notNullValue());
    }
    
    @Test
    public void greenTest() throws Exception {
        GeneratorAppConfig generatorAppConfig = new GeneratorAppConfig();
        assertThat(generatorAppConfig.readConfiguration(), notNullValue());
        assertThat(generatorAppConfig.readClassStructure(), notNullValue());
        assertThat(generatorAppConfig.mergeTemplate(), notNullValue());
        assertThat(generatorAppConfig.fileWriter(), notNullValue());
        assertThat(generatorAppConfig.prepareJava(), notNullValue());
        assertThat(generatorAppConfig.solveReferences(), notNullValue());
        assertThat(generatorAppConfig.configuration(), notNullValue());
    }
    
}
