package org.mwolff.functionalinterface;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.Command;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.CommandTransition;
import org.mwolff.command.DefaultCommandContainer;
import org.mwolff.command.chain.AbstractDefaultChainCommand;
import org.mwolff.command.chain.ChainCommand;
import org.mwolff.command.process.AbstractDefaultProcessCommand;
import org.mwolff.command.process.ProcessCommand;

public class FunctionInterfaceTest {
    
    @Test
    public void testCommand() throws Exception {
        String parameter = "Hallo Welt";
        CommandTransition result = execute((x) -> {
            System.out.println(parameter); 
            return CommandTransition.SUCCESS;
        }, parameter);
        assertThat(result, is(CommandTransition.SUCCESS));
    }

    @Test
    public void testChainCommanhd() throws Exception {
        ChainCommand<Object> command = new AbstractDefaultChainCommand<Object>() {
        };
        CommandTransition result = execute(command, "");
        assertThat(result, is(CommandTransition.DONE));
        
    }
    
    @Test
    public void testProcessCommand() throws Exception {
        ProcessCommand<Object> command = new AbstractDefaultProcessCommand<Object>() {
            @Override
            public String executeAsProcess(Object context) {
                return "FINE";
            }
        };
        
        command.setProcessID("Start");

        String result = execute(command, "Start");
        assertThat(result, nullValue());
        
    }
    
    public CommandTransition execute(Command<Object> object, Object parameter)  {
        return object.executeCommand(parameter);
    }

    public CommandTransition execute(ChainCommand<Object> object, Object parameter) {
        return object.executeCommandAsChain(null);
    }
    
    public String execute(ProcessCommand<Object> object, Object parameter) {
        
        CommandContainer<Object> container = new DefaultCommandContainer<>();
        container.addCommand(object);
        return container.executeAsProcess("Start", parameter);
    }

}
