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


import org.mwolff.generator.commands.FileWriter;
import org.mwolff.generator.commands.MergeTemplate;
import org.mwolff.generator.commands.PrepareJava;
import org.mwolff.generator.commands.ReadClassStructure;
import org.mwolff.generator.commands.ReadConfiguration;
import org.mwolff.generator.commands.SolveReferences;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "org.mwolff")
public class GeneratorAppConfig {

    @Bean(name = { "readConfiguration" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReadConfiguration<org.mwolff.generator.structures.Configuration> readConfiguration() {
        ReadConfiguration<org.mwolff.generator.structures.Configuration> configuration = new ReadConfiguration<org.mwolff.generator.structures.Configuration>();
        return configuration;
    }
    
    @Bean(name = { "readClassStructure" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReadClassStructure<org.mwolff.generator.structures.Configuration> readClassStructure() {
        return new ReadClassStructure<org.mwolff.generator.structures.Configuration>();
    }
    
    @Bean(name = { "mergeTemplate" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MergeTemplate<org.mwolff.generator.structures.Configuration> mergeTemplate() {
        return new MergeTemplate<org.mwolff.generator.structures.Configuration>();
    }
    
    @Bean(name = { "fileWriter" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FileWriter<org.mwolff.generator.structures.Configuration> fileWriter() {
        return new FileWriter<org.mwolff.generator.structures.Configuration>();
    }
    
    @Bean(name = { "prepareJava" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrepareJava<org.mwolff.generator.structures.Configuration> prepareJava() {
        return new PrepareJava<org.mwolff.generator.structures.Configuration>();
    }
    
    @Bean(name = { "solveReferences" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SolveReferences<org.mwolff.generator.structures.Configuration> solveReferences() {
        return new SolveReferences<org.mwolff.generator.structures.Configuration>();
    }
    
    @Bean(name = { "configuration" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public org.mwolff.generator.structures.Configuration configuration() {
        return new org.mwolff.generator.structures.Configuration();
    }
    

}