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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.mwolff.generator.structures.Configuration;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

public class FileWriter<T extends Configuration> extends AbstractDefaultChainCommand<T> {

    private static final Logger LOG = Logger.getLogger(FileWriter.class);

    @Override
    public void execute(T context) throws CommandException {

        File dir = new File(context.getActualOutputPath() + "/");

        if (!dir.exists() && (!dir.mkdirs())) {
            throw new CommandException("Directory could not created. Maybe you've no rights to create this directory.");
        }

        PrintWriter out = null;
        try {
            LOG.info("Writing file: " + context.getActualOutputPath() + "/" + context.getOutputFile());
            out = getWriter(context.getActualOutputPath() + "/" + context.getOutputFile());
        } catch (FileNotFoundException e) {
            throw new CommandException(e);
        }

        String v = Configuration.version;
        String t = Configuration.VERSION;
        t = t.replace("$version", v);
        out.write(t);
        out.write(context.getMergeString());
        out.close();
    }
    
    public PrintWriter getWriter(String s) throws FileNotFoundException{
        return new PrintWriter(s);
    }

}
