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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import de.mwolff.commons.command.iface.ParameterObject;

public class ConfigurationTest {

    private Configuration configuration;

    @Before
    public void setUp() {
        configuration = new Configuration();
    }

    @Test
    public void configationIsParameterObject() throws Exception {
        assertThat(configuration, CoreMatchers.instanceOf(ParameterObject.class));
    }

    @Test
    public void configurationProperties() throws Exception {
        configuration.setOutputPath("/my/output");
        configuration.setOutputFile("output.java");
        configuration.setConfiguration("/configuration.properties");
        configuration.setMergeString("myString");
        configuration.setXmlfile("class.xml");
        configuration.setClassTemplate("class-template.vm");
        configuration.setBasepath("/home/mwolff");
        configuration.setImports("import org.hallo.Address");
        configuration.setActualOutputPath("/home/mwolff/actual");
        configuration.setIdDefault("true");
        configuration.setHibernateSupport("true");
        configuration.setIdType("int");
        configuration.setPathToTemplate("src/test/resources");
        ClassStructure structure = new ClassStructure();
        configuration.setActualStructure(structure);
        List<ClassStructure> configList = new ArrayList<ClassStructure>();
        configuration.setConfigurationList(configList);
        assertThat(configuration.getOutputPath(), is("/my/output"));
        assertThat(configuration.getOutputFile(), is("output.java"));
        assertThat(configuration.getConfiguration(), is("/configuration.properties"));
        assertThat(configuration.getMergeString(), is("myString"));
        assertThat(configuration.getConfigurationList().size(), is(0));
        assertThat(configuration.getXmlfile(), is("class.xml"));
        assertThat(configuration.getClassTemplate(), is("class-template.vm"));
        assertThat(configuration.getBasepath(), is("/home/mwolff"));
        assertThat(configuration.getImports(), is("import org.hallo.Address"));
        assertThat(configuration.getActualOutputPath(), is("/home/mwolff/actual"));
        assertThat(configuration.getIdDefault(), is("true"));
        assertThat(configuration.getHibernateSupport(), is("true"));
        assertThat(configuration.getIdType(), is("int"));
        assertThat(configuration.getPathToTemplate(), is("src/test/resources"));
        assertThat(structure, is(configuration.getActualStructure()));
    }

}
