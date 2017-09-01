package org.mwolff.helloworld;

import org.mwolff.command.Command;
import org.mwolff.command.CommandException;

public class SecondCommand implements Command<ParameterObject>{

    @Override
    public void execute(ParameterObject parameterObject) throws CommandException {
        parameterObject.setValue(parameterObject.getValue() + " How are you!");
    }
    
}
