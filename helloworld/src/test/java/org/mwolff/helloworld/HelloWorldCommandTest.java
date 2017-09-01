package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldCommandTest {

    @Test
    public void testExecuteHelloWorld() throws Exception {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand();
        ParameterObject object = new ParameterObject();
        helloWorldCommand.execute(object);
        assertThat(object.getValue(), is("Hello World"));
    }
}
