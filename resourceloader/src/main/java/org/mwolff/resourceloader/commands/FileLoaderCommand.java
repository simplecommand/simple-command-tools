package org.mwolff.resourceloader.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.chain.AbstractDefaultChainCommand;

public class FileLoaderCommand extends AbstractDefaultChainCommand<String> {

    @Override
    public CommandTransition executeCommandAsChain(String parameterObject) {

        final String filename = parameterObject;
        try {
            new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            return CommandTransition.NEXT;
        }
        return CommandTransition.DONE;
    }

    @Override
    public CommandTransition executeCommand(String parameterObject) {
        throw new UnsupportedOperationException("Use executeCommandAsChain instead.");
   }

}
