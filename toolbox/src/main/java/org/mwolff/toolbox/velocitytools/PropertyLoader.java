package org.mwolff.toolbox.velocitytools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Loads a property file from the various sources.
 *
 * @author Manfred Wolff
 * @since 1.0
 *
 */
public class PropertyLoader {

    public enum Methods {
        CLASSPATH, FILE, DEFAULT
    }

    private final Properties properties = new Properties();

    public void initialize(final String resource, final Methods method) throws IOException {
        if (method == Methods.CLASSPATH) {
            loadPerClathpath(resource);
        }
        if (method == Methods.FILE) {
            loadPerFile(resource);
        }
    }

    public void loadPerFile(final String resource) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(resource);
        properties.load(fileInputStream);
    }

    public Properties getProperties() {
        return (Properties) properties.clone();
    }

    private void loadPerClathpath(final String resource) throws IOException {
        final InputStream is = this.getClass().getResourceAsStream(resource);
        if (is != null) {
            loadProperties(is);
        } else {
            throw new IOException("Property file does not exists");
        }
    }

    private void loadProperties(final InputStream is) throws IOException {
        properties.load(is);
    }

    /**
     * Gets the property and does String substitution.
     *
     * key=Welt key2=Hallo ${key}
     *
     * assertThat(propertyLoader.getProperty("key2"), is("Hallo Welt"));
     *
     * @param property
     *            The key of the property to get.
     * @return
     */
    public String getProperty(String property) {

        String toExermine = getProperties().getProperty(property);
        final Set<Object> keySet = getProperties().keySet();
        for (final Object object : keySet) {
            final String key = (String) object;
            if (toExermine.contains("${" + key + "}")) {
                final String value = getProperties().getProperty(key);
                toExermine = toExermine.replaceAll(Pattern.quote("${" + key + "}"), value);
            }
        }
        return toExermine;
    }
}
