package org.mwolff.velocitytools;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
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
    
    private static final Logger LOG = Logger.getLogger(VelocityMerger.class);

    private String template;
    private Properties properties;

    public void setTemplate(final String template) {
        this.template = template;
    }

    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    public String mergeWithPropertyFile() {
        final VelocityEngine velocityEngine = initializeVelocityEngine();
        final VelocityContext context = new VelocityContext();
        movePropertiesToContext(context);
        final StringWriter writer = mergeTemplateWithContext(velocityEngine, context);
        return writer.toString();
    }

    private VelocityEngine initializeVelocityEngine() {
        final VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        return ve;
    }

    private StringWriter mergeTemplateWithContext(final VelocityEngine velocityEngine, final VelocityContext context) {
        Template veloTemplate;
        veloTemplate = velocityEngine.getTemplate(this.template);
        final StringWriter writer = new StringWriter();
        try {
        veloTemplate.merge(context, writer);
        } catch (Throwable ex) {
            LOG.error(ex);
        }
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

    public String mergeWithContext(VelocityContext context) {
        final VelocityEngine velocityEngine = initializeVelocityEngine();
        final StringWriter writer = mergeTemplateWithContext(velocityEngine, context);
        return writer.toString();
    }

}
