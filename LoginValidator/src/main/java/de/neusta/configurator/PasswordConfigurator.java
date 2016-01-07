package de.neusta.configurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfigurator {
    
    private static final Logger LOG = Logger.getLogger(PasswordConfigurator.class);
    
    private Properties configuration;
    private static String CONFIGFILENAME = "/validator.configuration";
    
    public boolean loadConfiguration() throws IOException {
        InputStream is;
        configuration = new Properties();
        is = PasswordConfigurator.class.getResourceAsStream(CONFIGFILENAME);

        if (is != null) {
            configuration.load(is);
            LOG.info(CONFIGFILENAME + " sucessfully loaded.");
        } else {
            LOG.error(CONFIGFILENAME + " could not be loaded.");
        }
        return is != null ? true : false;
    }
    

    public Integer getPasswordLength() {
        if (configuration != null) {
            return Integer.valueOf(configuration.getProperty("lengthvalidator.length"));
        } else {
            return null;
        }
    }

}
