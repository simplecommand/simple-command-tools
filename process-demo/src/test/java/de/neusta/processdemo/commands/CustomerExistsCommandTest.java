package de.neusta.processdemo.commands;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import de.mwolff.commons.command.DefaultCommandContainer;
import de.mwolff.commons.command.DefaultTransition;
import de.mwolff.commons.command.iface.CommandContainer;
import de.mwolff.commons.command.iface.Transition;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class CustomerExistsCommandTest {

    @Test
    public void executeProcessNewsletter() throws Exception {

        ProcessParameter processParameter = new ProcessParameter();

        CustomerExistsCommand<ProcessParameter> customerExistsCommand = new CustomerExistsCommand<>();
        prepareCustomerExistsCommand(customerExistsCommand);
        
        processParameter.setStarted(true);
        processParameter.setCustomerExists(true);

        CommandContainer<ProcessParameter> commandContainer = new DefaultCommandContainer<>();
        commandContainer.addCommand(customerExistsCommand);
        
        commandContainer.executeAsProcess("CustomerExists", processParameter);
        assertThat(processParameter.getCustomerDecision(), is("NewsLetter"));
    }

    @Test
    public void executeProcessAddCustomer() throws Exception {

        ProcessParameter processParameter = new ProcessParameter();

        CustomerExistsCommand<ProcessParameter> customerExistsCommand = new CustomerExistsCommand<>();
        prepareCustomerExistsCommand(customerExistsCommand);
        
        processParameter.setStarted(true);
        processParameter.setCustomerExists(false);

        CommandContainer<ProcessParameter> commandContainer = new DefaultCommandContainer<>();
        commandContainer.addCommand(customerExistsCommand);
        
        commandContainer.executeAsProcess("CustomerExists", processParameter);
        assertThat(processParameter.getCustomerDecision(), is("AddCustomer"));
    }

    private void prepareCustomerExistsCommand(CustomerExistsCommand<ProcessParameter> customerExistsCommand) {
        customerExistsCommand.setProcessID("CustomerExists");
        Transition transition = new DefaultTransition();
        transition.setReturnValue("Ja");
        transition.setTarget("SendNewsletter");
        customerExistsCommand.addTransition(transition);
        transition = new DefaultTransition();
        transition.setReturnValue("Nein");
        transition.setTarget("AddCustomer");
        customerExistsCommand.addTransition(transition);
    }

}
