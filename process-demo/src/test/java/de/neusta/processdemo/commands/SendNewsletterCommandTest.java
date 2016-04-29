package de.neusta.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.mwolff.commons.command.DefaultCommandContainer;
import de.mwolff.commons.command.iface.CommandContainer;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class SendNewsletterCommandTest {

    @Test
    public void execute() throws Exception {
        ProcessParameter processParameter = new ProcessParameter();

        SendNewsletterCommand<ProcessParameter> newsletterCommand = new SendNewsletterCommand<>();
        newsletterCommand.setProcessID("SendNewsletter");

        processParameter.setStarted(true);
        processParameter.setCustomerExists(true);

        CommandContainer<ProcessParameter> commandContainer = new DefaultCommandContainer<>();
        commandContainer.addCommand(newsletterCommand);
        
        commandContainer.executeAsProcess("SendNewsletter", processParameter);
        assertThat(processParameter.isNewsletterHasToBeSent(), is(true));

    }
    

}
