package org.mwolff.helloworld;

import org.mwolff.command.AbstractDefaultCommand;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class SecondCommand extends AbstractDefaultCommand<GenericParameterObject>{

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
}
