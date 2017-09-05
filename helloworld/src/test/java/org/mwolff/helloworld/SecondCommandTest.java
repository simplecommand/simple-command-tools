package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.DefaultCommandContainer;
import org.mwolff.command.parameterobject.DefaultParameterObject;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class SecondCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExecute() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        SecondCommand instance = SecondCommand.getInstance();
        instance.execute(null);
    }

    
    @Test
    public void getInstanceTest() throws Exception {
        SecondCommand instance = SecondCommand.getInstance();
        assertThat(instance, notNullValue());
        assertThat(instance, CoreMatchers.instanceOf(SecondCommand.class));
    }

    
    @Test
    public void testHowAreYou() throws Exception {
        SecondCommand instance = SecondCommand.getInstance();
        GenericParameterObject object = DefaultParameterObject.getInstance();
        object.put("helloworld.message","");
        CommandTransition result = instance.executeCommand(object);
        assertThat(object.getAsString("helloworld.message"), is(" How are you?"));
        assertThat(result, is(CommandTransition.SUCCESS));
    }

    @Test
    public void testBothTogether() throws Exception {
        
        CommandContainer<GenericParameterObject> container = new DefaultCommandContainer<>();
        GenericParameterObject object = DefaultParameterObject.getInstance();
        container
            .addCommand(HelloWorldCommand.getInstance())
            .addCommand(SecondCommand.getInstance());
        CommandTransition result = container.executeCommand(object);
        assertThat(object.getAsString("helloworld.message"), is("Hello World How are you?"));
        assertThat(result, is(CommandTransition.SUCCESS));
    }
}
