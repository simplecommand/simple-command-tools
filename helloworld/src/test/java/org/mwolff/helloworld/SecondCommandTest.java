package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.DefaultCommandContainer;
import org.mwolff.command.parameterobject.DefaultParameterObject;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class SecondCommandTest {

    @Test
    public void getInstanceTest() throws Exception {
        SecondCommand instance = SecondCommand.getInstance();
        assertThat(instance, notNullValue());
        assertThat(instance, CoreMatchers.instanceOf(SecondCommand.class));
    }
    
    @Test
    public void testHowAreYou() throws Exception {
        GenericParameterObject parameterObject = DefaultParameterObject.getInstance();
        parameterObject.put("helloworld.message","");
        CommandTransition result = SecondCommand.getInstance().executeCommand(parameterObject);
        assertThat(parameterObject.getAsString("helloworld.message"), is(" How are you?"));
        assertThat(result, is(CommandTransition.SUCCESS));
    }

    @Test
    public void testBothTogether() throws Exception {
        
        CommandContainer<GenericParameterObject> container = new DefaultCommandContainer<>();
        GenericParameterObject parameterObject = DefaultParameterObject.getInstance();
        
        CommandTransition result = container
            .addCommand(HelloWorldCommand.getInstance())
            .addCommand(SecondCommand.getInstance())
            .executeCommand(parameterObject);
        
        assertThat(parameterObject.getAsString("helloworld.message"), is("Hello World How are you?"));
        assertThat(result, is(CommandTransition.SUCCESS));
    }
}
