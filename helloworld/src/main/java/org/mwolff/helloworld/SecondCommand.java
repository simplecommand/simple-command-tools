package org.mwolff.helloworld;

import org.mwolff.command.Command;
import org.mwolff.command.CommandException;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class SecondCommand implements Command<GenericParameterObject>{

    public static final SecondCommand getInstance() {
        return new SecondCommand();
    }

    @Override
    public CommandTransition executeCommand(GenericParameterObject parameterObject) {
        String message = parameterObject.getAsString("helloworld.message");
        message += " How are you?";
        parameterObject.put("helloworld.message", message);
        return CommandTransition.SUCCESS;
    }
    
    @Override
    public void execute(GenericParameterObject parameterObject) throws CommandException {
        throw new UnsupportedOperationException("Deprecated, use executeCommand instead.");
    }
    
}
