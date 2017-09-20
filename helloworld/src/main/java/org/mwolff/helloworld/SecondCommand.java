package org.mwolff.helloworld;

import org.mwolff.command.AbstractDefaultCommand;
import org.mwolff.command.CommandTransition;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class SecondCommand extends AbstractDefaultCommand<GenericParameterObject>{

    public static final String helloworld_message = "helloworld.message";
    
    public static final SecondCommand getInstance() {
        return new SecondCommand();
    }

    @Override
    public CommandTransition executeCommand(GenericParameterObject parameterObject) {
        String message = parameterObject.getAsString(helloworld_message);
        message += " How are you?";
        parameterObject.put(helloworld_message, message);
        return CommandTransition.SUCCESS;
    }
}
