package org.mwolff.generator.commands;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.DisplayTool;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.Configuration;
import org.mwolff.velocitytools.VelocityMerger;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

public class MergeTemplate<T extends Configuration> extends AbstractDefaultChainCommand<T> {
    
    private static final Logger LOG = Logger.getLogger(MergeTemplate.class);
    
    @Resource
    FileWriter<Configuration> fileWriter;
    
    @Resource
    PrepareJava<Configuration> prepareJava;
    

    @Override
    public void execute(T context) throws CommandException {

        List<ClassStructure> classStructureList = context.getConfigurationList();
        
        for (ClassStructure classStructure : classStructureList) {
            
            context.setActualStructure(classStructure);
            LOG.info("Merging class structure: " + classStructure.getIdentifier());
            LOG.info("Preparing structure for Java.");
            prepareJava.execute(context);
            LOG.info("Preparing structure for Java.");
            
            final VelocityMerger velocityMerger = new VelocityMerger();
            velocityMerger.setTemplate(context.getClassTemplate());
            VelocityContext veloContext = new VelocityContext();
            veloContext.put("classStructure", classStructure);
            veloContext.put("display",new DisplayTool());
            final String result = velocityMerger.mergeWithContext(veloContext);
            context.setMergeString(result);
            LOG.info("Result of merging is: \n" + result);
            fileWriter.execute(context);
        }
    }
}
