package de.neusta.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.mwolff.commons.command.DefaultCommandContainer;
import de.mwolff.commons.command.DefaultTransition;
import de.mwolff.commons.command.iface.CommandContainer;
import de.mwolff.commons.command.iface.Transition;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class StartCommandTest {
    
    @Test
    public void isStartet() throws Exception {
        ProcessParameter processParameter = new ProcessParameter();

        StartCommand<ProcessParameter> startCommand = new StartCommand<>();
        startCommand.setProcessID("Start");
        Transition transition = new DefaultTransition();
        transition.setReturnValue("OK");
        transition.setTarget("CustomerExists");
        startCommand.addTransition(transition);
        
        processParameter.setStarted(false);

        CommandContainer<ProcessParameter> commandContainer = new DefaultCommandContainer<>();
        commandContainer.addCommand(startCommand);
        
        String next = commandContainer.executeAsProcess("Start", processParameter);
        assertThat(processParameter.isStarted(), is(true));
        
    }
 
    
}
