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
package org.mwolff.generator.xml;

import static org.hamcrest.CoreMatchers.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.InstanceVariable;


public class XMLHelperTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void chainbuilderExists() throws Exception {
        final XMLHelper xmlhelper = new XMLHelper();
        Assert.assertThat(xmlhelper, CoreMatchers.instanceOf(XMLHelper.class));
    }
    
    @Test
    public void loadInvalidXMLFileFromInputStream() throws Exception {
        thrown.expect(FileNotFoundException.class);
        //thrown.expectMessage("Could not read xml file");
        final XMLHelper xmlhelper = new XMLHelper();
        xmlhelper.setXmlFileName("/notExists.xml");
        xmlhelper.readXMLFile();
    }

    @Test
    public void loadInvalidXMLFileFormat() throws Exception {
        thrown.expect(FileNotFoundException.class);
        //thrown.expectMessage("XML Document could not created");
        final XMLHelper xmlhelper = new XMLHelper();
        xmlhelper.setXmlFileName("/invalidCommandChain.xml");
        xmlhelper.readXMLFile();
    }
    
    
    @Test
    public void classelements() throws Exception {
        final XMLHelper xmlhelper = new XMLHelper();
        xmlhelper.setXmlFileName("src/test/resources/class2.xml");
        
        List<ClassStructure> classList = xmlhelper.readXMLFile();
        Assert.assertThat(classList.size(), CoreMatchers.is(2));
        ClassStructure structure = classList.get(0);
        Assert.assertThat(structure.getIdentifier(), CoreMatchers.is("GeneratedClassStructure"));
        Assert.assertThat(structure.getPackageString(), CoreMatchers.is("gen.org.mwolff.generator.commands"));
        Assert.assertThat(structure.getClassComment(), CoreMatchers.is("Structure of a class to generate."));
        Assert.assertThat(structure.getAuthor(), CoreMatchers.is("generator 0.0.2"));
        Assert.assertThat(structure.getVersion(), CoreMatchers.is("1.0.0"));
        Assert.assertThat(structure.getTemplate(), CoreMatchers.is("class-template.vm"));
        
        List<InstanceVariable> instanceList = structure.getInstanceVariableList();
        Assert.assertThat(instanceList.size(), CoreMatchers.is(9));
        InstanceVariable variable = instanceList.get(0);
        Assert.assertThat(variable.getIdentifier(), CoreMatchers.is("identifier"));
        Assert.assertThat(variable.getType(), CoreMatchers.is("String"));
        Assert.assertThat(variable.getModifier(), CoreMatchers.is("private"));

        ClassStructure structure2 = classList.get(1);
        List<InstanceVariable> instanceList2 = structure2.getInstanceVariableList();
        InstanceVariable variable2 = instanceList2.get(0);
        Assert.assertThat(variable2.getCardinality(), is("none"));
    }
}
