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
            loader.initialize(context.getConfiguration(), Methods.FILE);
        } catch (Exception ex) {
            LOG.error("Error reading configuration file " + context.getConfiguration(), ex);
            throw new CommandException(ex);
        }
        
        LOG.info("Setting configuration information into context");
        context.setOutputPath(loader.getProperty("outputpath"));
        LOG.info("outputpath: " + context.getOutputPath());
        context.setClassTemplate(loader.getProperty("classtemplate"));
        LOG.info("classtemplate: " + context.getClassTemplate());
        context.setPathToTemplate(loader.getProperty("pathtotemplates"));
        LOG.info("classtemplate: " + context.getClassTemplate());
        context.setBasepath(loader.getProperty("basepath"));
        LOG.info("basepath: " + context.getBasepath());
        context.setXmlfile(loader.getProperty("xmlfile"));
        LOG.info("xmlfile: " + context.getXmlfile());
        context.setIdDefault(loader.getProperty("iddefault"));
        LOG.info("iddefault: " + context.getIdDefault());
        context.setHibernateSupport(loader.getProperty("hibernatesupport"));
        LOG.info("hibernatesupport: " + context.getHibernateSupport());
        String idtype = loader.getProperty("idtype");
        if ("".equals(idtype)) {
            idtype = "int";
        }
        context.setIdType(idtype);
        LOG.info("id type: " + context.getIdType());
        
    }
}
