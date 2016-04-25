package org.mwolff.generator.structures;

import static org.hamcrest.Matchers.is;
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
        assertThat(structure, is(configuration.getActualStructure()));
    }

}
