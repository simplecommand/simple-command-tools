package org.mwolff.resourceloader.commands;

import java.io.InputStream;

import org.mwolff.command.CommandTransition;
import org.mwolff.command.chain.AbstractDefaultChainCommand;

public class ClassLoaderCommand extends AbstractDefaultChainCommand<String>{

    @Override
    public CommandTransition executeCommandAsChain(String parameterObject) {
        final String filename = parameterObject;
        final InputStream inputStream = this.getClass().getResourceAsStream("/" + filename);
        if (inputStream == null) {
            return CommandTransition.NEXT;
        }
        return CommandTransition.FAILURE;
    }
}
