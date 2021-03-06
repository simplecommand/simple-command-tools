package org.mwolff.helloworld;

import org.mwolff.command.Command;
import org.mwolff.command.CommandTransition;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class HelloWorldCommand implements Command<GenericParameterObject>{
    
    public static final HelloWorldCommand getInstance() {
        return new HelloWorldCommand();
    }
    
    @Override
    public CommandTransition executeCommand(GenericParameterObject parameterObject) {
        parameterObject.put("helloworld.message","Hello World");
        return CommandTransition.SUCCESS;
    }
}
 
