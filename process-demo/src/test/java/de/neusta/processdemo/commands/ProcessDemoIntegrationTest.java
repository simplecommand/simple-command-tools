package de.neusta.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.mwolff.command.chainbuilder.XMLChainBuilder;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class ProcessDemoIntegrationTest {

    @Test
    public void processWithNewsletter() throws Exception {
        final XMLChainBuilder<ProcessParameter> xmlChainBuilder = new XMLChainBuilder<>("/commandChainProcess.xml");
        final ProcessParameter context = new ProcessParameter();
        context.setCustomerExists(true);
        String returnvalue = xmlChainBuilder.executeAsProcess("Start", context);
        assertThat(returnvalue, is("END"));
        assertThat(context.isCustomerHasToBeAdded(), is(false));
        assertThat(context.isNewsletterHasToBeSent(), is(true));
    }

    @Test
    public void processAddCustomer() throws Exception {
        final XMLChainBuilder<ProcessParameter> xmlChainBuilder = new XMLChainBuilder<>("/commandChainProcess.xml");
        final ProcessParameter context = new ProcessParameter();
        context.setCustomerExists(false);
        String returnvalue = xmlChainBuilder.executeAsProcess("Start", context);
        assertThat(returnvalue, is("END"));
        assertThat(context.isCustomerHasToBeAdded(), is(true));
        assertThat(context.isNewsletterHasToBeSent(), is(false));
    }

}
