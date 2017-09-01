package org.mwolff.processdemo.commands;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class IdentifyCustomerProcessTest {

    @Test
    public void testFailure() throws Exception {
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setCustomerAccountNumber("42");
        IdentifyCustomerProcess identifyCustomerProcess = new IdentifyCustomerProcess();
        String next = identifyCustomerProcess.executeAsProcess(paymentParameterObject);
        assertThat(next, CoreMatchers.is("FAIL"));
    }
    
    @Test
    public void testPremiumCustomer() throws Exception {
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setCustomerAccountNumber("41");
        IdentifyCustomerProcess identifyCustomerProcess = new IdentifyCustomerProcess();
        String next = identifyCustomerProcess.executeAsProcess(paymentParameterObject);
        assertThat(next, CoreMatchers.is("PREMIUM"));
    }

    @Test
    public void testNoPremiumCustomer() throws Exception {
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setCustomerAccountNumber("40");
        IdentifyCustomerProcess identifyCustomerProcess = new IdentifyCustomerProcess();
        String next = identifyCustomerProcess.executeAsProcess(paymentParameterObject);
        assertThat(next, CoreMatchers.is("SUCCESS"));
    }
}
