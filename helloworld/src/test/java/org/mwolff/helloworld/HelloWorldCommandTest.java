package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.parameterobject.DefaultParameterObject;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class HelloWorldCommandTest {

    /**
     * Tests if getInstance() returns unique instances and the right interface.
     */
    @Test
    public void getInstanceTest() throws Exception {
        HelloWorldCommand hello = HelloWorldCommand.getInstance();
        HelloWorldCommand world = HelloWorldCommand.getInstance();
        assertThat(HelloWorldCommand.getInstance(), instanceOf(HelloWorldCommand.class));
        assertThat(hello, is(not(world)));
    }

    /**
     * Tests if the command sets a token named helloworld.message with the right value. 
     */
    @Test
    public void testExecuteHelloWorld() throws Exception {
        GenericParameterObject context = DefaultParameterObject.getInstance();
        CommandTransition result = HelloWorldCommand.getInstance().executeCommand(context);
        assertThat(result, is(CommandTransition.SUCCESS));
        assertThat(context.getAsString("helloworld.message"), is("Hello World"));
    }
}
