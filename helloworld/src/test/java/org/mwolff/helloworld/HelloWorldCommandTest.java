package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.parameterobject.DefaultParameterObject;
import org.mwolff.command.parameterobject.GenericParameterObject;

public class HelloWorldCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExecute() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        HelloWorldCommand instance = HelloWorldCommand.getInstance();
        instance.execute(null);
    }
    
    @Test
    public void getInstanceTest() throws Exception {
        HelloWorldCommand instance = HelloWorldCommand.getInstance();
        assertThat(instance, notNullValue());
        assertThat(instance, CoreMatchers.instanceOf(HelloWorldCommand.class));
    }
    
    @Test
    public void testExecuteHelloWorld() throws Exception {
        HelloWorldCommand helloWorldCommand = HelloWorldCommand.getInstance();
        GenericParameterObject context = DefaultParameterObject.getInstance();
        CommandTransition result = helloWorldCommand.executeCommand(context);
        assertThat(result, is(CommandTransition.SUCCESS));
        assertThat(context.getAsString("helloworld.message"), is("Hello World"));
    }
}
