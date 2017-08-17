package org.mwolff.toolbox.velocitytools;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.junit.Assert;
import org.junit.Test;
import org.mwolff.toolbox.velocitytools.PropertyLoader.Methods;

public class VelocityMergerTest {

    private static final Logger LOG = Logger.getLogger(VelocityMergerTest.class);

    @Test
    public void mergeSimpleString() throws Exception {
        final String s = "We are using $project $name to render this.";
        final VelocityContext context = new VelocityContext();
        context.put("project", "MyProject");
        context.put("name", "MyName");
        final StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        Assert.assertEquals("We are using MyProject MyName to render this.", w.toString());
    }

    @Test
    public void mergeSimpleKey() throws Exception {
        VelocityMergerTest.LOG.debug("mergeSimpleKey");
        final VelocityMerger velocityMerger = new VelocityMerger();
        velocityMerger.setTemplate("simplemerge.vm");
        final PropertyLoader propLoader = new PropertyLoader();
        propLoader.initialize("/example.properties", Methods.CLASSPATH);
        final Properties properties = propLoader.getProperties();
        velocityMerger.setProperties(properties);
        final String result = velocityMerger.mergeWithPropertyFileWithFilePath("src/test/resources");
        Assert.assertEquals("Hallo meine Welt ${key} merged.", result);
    }

    @Test
    public void mergeClassPerPropertyfile() throws Exception {
        VelocityMergerTest.LOG.debug("mergeClassPerPropertyfile");
        final VelocityMerger velocityMerger = new VelocityMerger();
        velocityMerger.setTemplate("specification.vm");
        final PropertyLoader propLoader = new PropertyLoader();
        propLoader.initialize("/specification.properties", Methods.CLASSPATH);
        final Properties properties = propLoader.getProperties();
        velocityMerger.setProperties(properties);
        final String result = velocityMerger.mergeWithPropertyWithClassPath();

        final String lineSeparator = System.getProperty("line.separator");

        final String expected = "" + "/**" + lineSeparator + " * Dies ist ein kleiner Test." + lineSeparator + " *"
                + lineSeparator + " * @author: Manfred Wolff" + lineSeparator + " * @version: 1.0" + lineSeparator
                + " */" + lineSeparator + " public class TestClass  extends Extends implements Implements {"
                + lineSeparator + " }";

        System.out.println(expected);
        System.out.println(result);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void mergeTemplateWithVelocityContext() throws Exception {
        VelocityMergerTest.LOG.debug("mergeTemplateWithVelocityContext");
        final VelocityMerger velocityMerger = new VelocityMerger();
        velocityMerger.setTemplate("specification.vm");
        final VelocityContext context = new VelocityContext();
        context.put("classcomment", "Dies ist ein kleiner Test.");
        context.put("author", "Manfred Wolff");
        context.put("version", "1.0.0");
        context.put("classname", "TestClass");
        final String result = velocityMerger.mergeWithContext(context, "src/test/resources");

        final String lineSeparator = System.getProperty("line.separator");

        final String expected = "" + "/**" + lineSeparator + " * Dies ist ein kleiner Test." + lineSeparator + " *"
                + lineSeparator + " * @author: Manfred Wolff" + lineSeparator + " * @version: 1.0.0" + lineSeparator
                + " */" + lineSeparator + " public class TestClass {" + lineSeparator + " }";

        Assert.assertEquals(expected, result);
    }
}
