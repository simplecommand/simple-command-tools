package org.mwolff.generator.commands;

import org.mwolff.generator.structures.Configuration;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

public class PrepareJava<T extends Configuration> extends AbstractDefaultChainCommand<T> {

    @Override
    public void execute(T context) throws CommandException {

        String base = context.getOutputPath() + "/";
        String toSplit = context.getActualStructure().getPackageString();
        toSplit = toSplit.replace('.', '/');
        context.setActualOutputPath(base + toSplit);
        
        String outputFile = context.getActualStructure().getIdentifier() + ".java";
        context.setOutputFile(outputFile);
        
    }

}
