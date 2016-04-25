package org.mwolff.generator.commands;

import org.apache.log4j.Logger;
import org.mwolff.generator.structures.Configuration;
import org.mwolff.velocitytools.PropertyLoader;
import org.mwolff.velocitytools.PropertyLoader.Methods;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

public class ReadConfiguration<T extends Configuration> extends AbstractDefaultChainCommand<T> {
    
    private static final Logger LOG = Logger.getLogger(ReadConfiguration.class);
    
    @Override
    public void execute(T context) throws CommandException {
        
        LOG.info("Reading property file out of classpath: " + context.getConfiguration());
        PropertyLoader loader = new PropertyLoader();
        try {
            loader.initialize(context.getConfiguration(), Methods.CLASSPATH);
        } catch (Exception ex) {
            LOG.error("Error reading configuration file " + context.getConfiguration(), ex);
            throw new CommandException(ex);
        }
        
        LOG.info("Setting configuration information into context");
        context.setOutputPath(loader.getProperties().getProperty("outputpath"));
        LOG.info("outputpath: " + context.getOutputPath());
        context.setClassTemplate(loader.getProperties().getProperty("classtemplate"));
        LOG.info("classtemplate: " + context.getClassTemplate());
        context.setBasepath(loader.getProperties().getProperty("basepath"));
        LOG.info("basepath: " + context.getBasepath());
        context.setXmlfile(loader.getProperties().getProperty("xmlfile"));
        LOG.info("xmlfile: " + context.getXmlfile());
        context.setIdDefault(loader.getProperties().getProperty("iddefault"));
        LOG.info("iddefault: " + context.getIdDefault());
        context.setHibernateSupport(loader.getProperties().getProperty("hibernatesupport"));
        LOG.info("hibernatesupport: " + context.getHibernateSupport());
        
    }
}
