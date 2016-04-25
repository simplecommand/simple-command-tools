package org.mwolff.velocitytools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads a property file from the various sources.
 *
 * @author Manfred Wolff
 * @since 1.0
 *
 */
public class PropertyLoader {

    public enum Methods {
        CLASSPATH, DEFAULT
    }

    private final Properties properties = new Properties();

    public void initialize(final String resource, final Methods method) throws IOException {
        if (method == Methods.CLASSPATH)
            loadPerClathpath(resource);
    }

    public Properties getProperties() {
        return (Properties) this.properties.clone();
    }

    private void loadPerClathpath(final String resource)  throws IOException {
        final InputStream is = this.getClass().getResourceAsStream(resource);
        if (is != null) {
            loadProperties(is);
        } else {
            throw new IOException("Property file does not exists");
        }
            
    }

    private void loadProperties(final InputStream is) throws IOException{
            this.properties.load(is);
    }

}