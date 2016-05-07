package org.mwolff.velocitytools;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Merges a velocity template with properties of the property file.
 * 
 * @author Manfred Wolff
 * @since 1.0
 *
 */
@Component
public class VelocityMerger {

    private String template;
    private Properties properties;

    public void setTemplate(final String template) {
        this.template = template;
    }

    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    public String mergeWithPropertyFile(final String pathToTemplate) throws VelocityException {
        final VelocityEngine velocityEngine = initializeVelocityEngine(pathToTemplate);
        final VelocityContext context = new VelocityContext();
        movePropertiesToContext(context);
        StringWriter writer = mergeTemplateWithContext(velocityEngine, context);
        return writer.toString();
    }

    private VelocityEngine initializeVelocityEngine(final String pathToTemplate) {
        final VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, pathToTemplate);
        return ve;
    }
    
    private VelocityEngine initializeVelocityEngine() {
        final VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        return ve;
    }


    private StringWriter mergeTemplateWithContext(final VelocityEngine velocityEngine, final VelocityContext context)
            throws VelocityException {
        Template veloTemplate;
        veloTemplate = velocityEngine.getTemplate(this.template);
        final StringWriter writer = new StringWriter();
        veloTemplate.merge(context, writer);
        return writer;
    }

    private void movePropertiesToContext(final VelocityContext context) {
        for (final String prop : this.properties.stringPropertyNames()) {
            final String value = this.properties.getProperty(prop);
            context.put(prop, value);
        }
    }

    /**
     * Only for test purposes to test all the fragments.
     */
    public static void evaluate(VelocityContext context, StringWriter w, String s) {
        Velocity.evaluate(context, w, "mystring", s);
    }

    public String mergeWithContext(final VelocityContext context, final String pathToTemplate) throws VelocityException {
        final VelocityEngine velocityEngine = initializeVelocityEngine(pathToTemplate);
        final StringWriter writer = mergeTemplateWithContext(velocityEngine, context);
        return writer.toString();
    }

}
