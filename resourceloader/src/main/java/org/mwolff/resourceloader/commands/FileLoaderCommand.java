package org.mwolff.resourceloader.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.mwolff.command.CommandTransition;
import org.mwolff.command.chain.AbstractDefaultChainCommand;

public class FileLoaderCommand extends AbstractDefaultChainCommand<String> {

    @Override
    public CommandTransition executeCommandAsChain(String parameterObject) {

        try {
            new FileInputStream(new File(parameterObject));
        } catch (FileNotFoundException e) {
            return CommandTransition.NEXT;
        }
        return CommandTransition.DONE;
    }
}
