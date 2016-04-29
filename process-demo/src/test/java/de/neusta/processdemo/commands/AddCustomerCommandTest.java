package de.neusta.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.mwolff.commons.command.DefaultCommandContainer;
import de.mwolff.commons.command.iface.CommandContainer;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class AddCustomerCommandTest {

    @Test
    public void execute() throws Exception {
        ProcessParameter processParameter = new ProcessParameter();

        AddCustomerCommand<ProcessParameter> addCustomerCommand = new AddCustomerCommand<>();
        addCustomerCommand.setProcessID("AddCustomer");

        processParameter.setStarted(true);
        processParameter.setCustomerExists(true);

        CommandContainer<ProcessParameter> commandContainer = new DefaultCommandContainer<>();
        commandContainer.addCommand(addCustomerCommand);
        
        commandContainer.executeAsProcess("AddCustomer", processParameter);
        assertThat(processParameter.isCustomerHasToBeAdded(), is(true));

    }

}
