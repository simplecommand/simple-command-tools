package org.mwolff.resourceloader.commands;

import java.io.InputStream;

import org.mwolff.command.CommandException;
import org.mwolff.command.chain.AbstractDefaultChainCommand;

public class ClassLoaderCommand extends AbstractDefaultChainCommand<String>{

    @Override
    public boolean executeAsChain(String parameterObject) {
        final String filename = parameterObject;
        final InputStream inputStream = this.getClass().getResourceAsStream("/" + filename);
        if (inputStream == null) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String parameterObject) throws CommandException {
        throw new UnsupportedOperationException("Use executeAsChain instead.");
    }
    
}
