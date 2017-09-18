package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.parameterobject.DefaultParameterObject;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class HelloWorldCommandTest {

    @Test
    public void getInstanceTest() throws Exception {
        assertThat(HelloWorldCommand.getInstance(), instanceOf(HelloWorldCommand.class));
    }
    
    @Test
    public void testExecuteHelloWorld() throws Exception {
        GenericParameterObject context = DefaultParameterObject.getInstance();
        CommandTransition result = HelloWorldCommand.getInstance().executeCommand(context);
        assertThat(result, is(CommandTransition.SUCCESS));
        assertThat(context.getAsString("helloworld.message"), is("Hello World"));
    }
}
