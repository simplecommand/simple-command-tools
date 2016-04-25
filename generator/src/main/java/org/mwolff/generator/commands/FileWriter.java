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
