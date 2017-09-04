package org.mwolff.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
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
    
    @Test
    public void testTestMode() throws Exception {
        IdentifyCustomerProcess identifyCustomerProcess = new IdentifyCustomerProcess();
        identifyCustomerProcess.setProcessID("IDENTIFY");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setTestmode(true);
        paymentParameterObject.setCustomerAccountNumber("40");
        String next = identifyCustomerProcess.executeAsProcess(paymentParameterObject);
        assertThat(next, CoreMatchers.is("SUCCESS"));
        String breadCrumb = paymentParameterObject.getBreadCrumb();
        assertThat(breadCrumb, is(" => IDENTIFY"));
    }
}
