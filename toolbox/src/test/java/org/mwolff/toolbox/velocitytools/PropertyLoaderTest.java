package org.mwolff.toolbox.velocitytools;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mwolff.toolbox.velocitytools.PropertyLoader.Methods;

public class PropertyLoaderTest {

    private PropertyLoader   propertyLoader;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        propertyLoader = new PropertyLoader();
    }

    @Test
    public void testEnumerationForCoverage() throws Exception {
        // just a bug in eclemma
        final Methods[] methods = Methods.values();
        Assert.assertEquals(3, methods.length);
        final Methods method = Methods.valueOf("CLASSPATH");
        Assert.assertEquals(Methods.CLASSPATH, method);
    }

    @Test
    public void propertyLoadedFromClasspath() throws Exception {
        propertyLoader.initialize("/example.properties", Methods.CLASSPATH);
        Assert.assertEquals("value", propertyLoader.getProperty("key"));
        Assert.assertEquals("meine Welt value", propertyLoader.getProperty("Welt"));
    }

    @Test
    public void propertyResourceNotExsists() throws Exception {
        thrown.expect(IOException.class);
        propertyLoader.initialize("/notExists.properties", Methods.CLASSPATH);
        Assert.assertEquals(null, propertyLoader.getProperty("key"));
    }

    @Test
    public void propertyLoaderFromFileMaven() throws Exception {
        propertyLoader.initialize("src/test/resources/example.properties", Methods.FILE);
        Assert.assertEquals("value", propertyLoader.getProperty("key"));
        Assert.assertEquals("meine Welt value", propertyLoader.getProperty("Welt"));
    }
}
