package org.mwolff.helloworld;

import org.mwolff.command.Command;
import org.mwolff.command.CommandException;

public class HelloWorldCommand implements Command<ParameterObject>{

    @Override
    public void execute(ParameterObject parameterObject) throws CommandException {
        parameterObject.setValue("Hello World");
    }

}
