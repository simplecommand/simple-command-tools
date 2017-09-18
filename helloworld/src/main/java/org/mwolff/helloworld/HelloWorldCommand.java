package org.mwolff.helloworld;

import org.mwolff.command.AbstractDefaultCommand;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class HelloWorldCommand extends AbstractDefaultCommand<GenericParameterObject>{
    
    public static final HelloWorldCommand getInstance() {
        return new HelloWorldCommand();
    }
    
    @Override
    public CommandTransition executeCommand(GenericParameterObject parameterObject) {
        parameterObject.put("helloworld.message","Hello World");
        return CommandTransition.SUCCESS;
    }
}
