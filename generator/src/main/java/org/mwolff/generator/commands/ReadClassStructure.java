package org.mwolff.generator.commands;

import java.util.List;

import org.apache.log4j.Logger;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.Configuration;
import org.mwolff.generator.xml.XMLHelper;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

public class ReadClassStructure<T extends Configuration> extends AbstractDefaultChainCommand<T> {
    
    private static final Logger LOG = Logger.getLogger(ReadClassStructure.class);

    @Override
    public void execute(T context) throws CommandException {
        
        final XMLHelper xmlhelper = new XMLHelper();
        xmlhelper.setXmlFileName(context.getXmlfile());
        
        LOG.info("Reading information in xml: " + context.getXmlfile());
        
        List<ClassStructure> classList = null;
        try {
            classList = xmlhelper.readXMLFile();
        } catch (Exception e) {
            throw new CommandException(e);
        }
        
        for (ClassStructure classStructure : classList) {
            if ("true".equals(context.getHibernateSupport())) {
                classStructure.setHibernate("true"); 
            } else {
                classStructure.setHibernate("false"); 
                
            }
        }
        
        context.setConfigurationList(classList);
    }
}
