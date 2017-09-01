package org.mwolff.helloworld;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.DefaultCommandContainer;

public class SecondCommandTest {

    @Test
    public void testHowAreYou() throws Exception {
        SecondCommand secondCommand = new SecondCommand();
        ParameterObject object = new ParameterObject();
        object.setValue("");
        secondCommand.execute(object);
        assertThat(object.getValue(), is(" How are you!"));
    }

    @Test
    public void testBothTogether() throws Exception {
        
        CommandContainer<ParameterObject> container = new DefaultCommandContainer<>();
        ParameterObject object = new ParameterObject();
        container
            .addCommand(new HelloWorldCommand())
            .addCommand(new SecondCommand());
        container.execute(object);
        assertThat(object.getValue(), is("Hello World How are you!"));
    }
}
