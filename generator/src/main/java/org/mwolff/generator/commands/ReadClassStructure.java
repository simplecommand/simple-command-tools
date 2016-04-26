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
