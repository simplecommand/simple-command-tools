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

import org.junit.Before;
import org.junit.Test;

public class InstanceVariableTest {

    InstanceVariable instanceVariable;

    @Before
    public void setUp() {
        instanceVariable = new InstanceVariable();
    }

    @Test
    public void modifier() throws Exception {
        instanceVariable.setModifier("private");
        assertThat("private", is(instanceVariable.getModifier()));
    }

    @Test
    public void type() throws Exception {
        instanceVariable.setType("String");
        assertThat("String", is(instanceVariable.getType()));
    }

    @Test
    public void identifier() throws Exception {
        instanceVariable.setIdentifier("myVariable");
        assertThat("myVariable", is(instanceVariable.getIdentifier()));
    }
    
    @Test
    public void cardinality() throws Exception {
        instanceVariable.setCardinality("one-many");
        assertThat("one-many", is(instanceVariable.getCardinality()));

    }

}
