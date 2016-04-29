package de.neusta.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.mwolff.commons.command.DefaultTransition;
import de.mwolff.commons.command.iface.Transition;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class StartCommandTest {
    
    @Test
    public void isStartet() throws Exception {
        ProcessParameter processParameter = new ProcessParameter();
        StartCommand<ProcessParameter> startCommand = createStartCommand();
        processParameter.setStarted(false);
        String next = startCommand.executeAsProcess("Start", processParameter);
        assertThat(processParameter.isStarted(), is(true));
        assertThat(next, is("OK"));
        
    }

    private StartCommand<ProcessParameter> createStartCommand() {
        StartCommand<ProcessParameter> startCommand = new StartCommand<>();
        startCommand.setProcessID("Start");
        Transition transition = createTransition();
        startCommand.addTransition(transition);
        return startCommand;
    }

    private Transition createTransition() {
        Transition transition = new DefaultTransition();
        transition.setReturnValue("OK");
        transition.setTarget("CustomerExists");
        return transition;
    }
}
