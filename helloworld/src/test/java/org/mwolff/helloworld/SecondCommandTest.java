package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.CommandTransition;
import org.mwolff.command.DefaultCommandContainer;
import org.mwolff.command.parameterobject.DefaultParameterObject;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class SecondCommandTest {

    @Test
    public void getInstanceTest() throws Exception {
        SecondCommand instance = SecondCommand.getInstance();
        assertThat(instance, instanceOf(SecondCommand.class));
    }
    
    @Test
    public void testHowAreYou() throws Exception {
        GenericParameterObject parameterObject = DefaultParameterObject.getInstance();
        parameterObject.put(SecondCommand.helloworld_message,"");
        CommandTransition result = SecondCommand.getInstance().executeCommand(parameterObject);
        assertThat(parameterObject.getAsString(SecondCommand.helloworld_message), is(" How are you?"));
        assertThat(result, is(CommandTransition.SUCCESS));
    }
    
    @Test
    public void testBothTogether() throws Exception {
        
        GenericParameterObject parameterObject = DefaultParameterObject.getInstance();
        CommandContainer<GenericParameterObject> container = new DefaultCommandContainer<>();
        
        CommandTransition result = container
            .addCommand(HelloWorldCommand.getInstance())
            .addCommand(SecondCommand.getInstance())
            .executeCommand(parameterObject);
        
        assertThat(parameterObject.getAsString(SecondCommand.helloworld_message), is("Hello World How are you?"));
        assertThat(result, is(CommandTransition.SUCCESS));
    }
}
