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
