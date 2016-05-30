/**
    Simple Generator Framework.
    Bases on Simple Command Framework

    Framework for easy source code generation via velocity
    @author Manfred Wolff <m.wolff@neusta.de>
    Download: https://github.com/simplecommand/SimpleCommandFramework-Examples


    Copyright (C) 2016 neusta software development

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
    USA
 */
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
            velocityMerger.setTemplate(classStructure.getTemplate());
            VelocityContext veloContext = new VelocityContext();
            veloContext.put("classStructure", classStructure);
            veloContext.put("display",new DisplayTool());
            String result = null;
            try {
                result = velocityMerger.mergeWithContext(veloContext, context.getPathToTemplate());
            } catch (Exception e) {
                throw new CommandException(e);
            }
            context.setMergeString(result);
            LOG.info("Result of merging is: \n" + result);
            fileWriter.execute(context);
        }
    }
}
