package org.mwolff.resourceloader.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.mwolff.command.CommandException;
import org.mwolff.command.chain.ChainCommand;

public class FileLoaderCommand implements ChainCommand<String> {

    /**
     * executeAsChain() has to return true, if the next command should overtake,
     * false otherwise. So actually false means that the issue of the whole
     * chain is solved.
     */
    @Override
    public boolean executeAsChain(String parameterObject) {

        final String filename = parameterObject;
        try {
            new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String parameterObject) throws CommandException {
        throw new UnsupportedOperationException("Use executeAsChain instead.");
    }

}
