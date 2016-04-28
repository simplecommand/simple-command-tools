package org.mwolff.velocitytools;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mwolff.velocitytools.PropertyLoader.Methods;

public class PropertyLoaderTest {

    private PropertyLoader propertyLoader;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        propertyLoader = new PropertyLoader();
    }

    @Test
    public void testEnumerationForCoverage() throws Exception {
        // just a bug in eclemma
        Methods[] methods = Methods.values();
        assertEquals(3, methods.length);
        Methods method = Methods.valueOf("CLASSPATH");
        assertEquals(Methods.CLASSPATH, method);
    }

    @Test
    public void propertyLoadedFromClasspath() throws Exception {
        propertyLoader.initialize("/example.properties", Methods.CLASSPATH);
        assertEquals("value", propertyLoader.getProperty("key"));
        assertEquals("meine Welt value", propertyLoader.getProperty("Welt"));
    }

    @Test
    public void propertyResourceNotExsists() throws Exception {
        thrown.expect(IOException.class);
        propertyLoader.initialize("/notExists.properties", Methods.CLASSPATH);
        assertEquals(null, propertyLoader.getProperty("key"));
    }
    
    @Test
    public void propertyLoaderFromFileMaven() throws Exception {
        propertyLoader.initialize("src/test/resources/example.properties", Methods.FILE);
        assertEquals("value", propertyLoader.getProperty("key"));
        assertEquals("meine Welt value", propertyLoader.getProperty("Welt"));
    }
}
