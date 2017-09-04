package org.mwolff.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import junit.framework.TestCase;

public class SuccessProcessCommandTest extends TestCase {
    
    @Test
    public void testExecuteMethod() throws Exception {
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        InitiatePaymentCommand initiatePaymentCommand = new InitiatePaymentCommand();
        String next = initiatePaymentCommand.executeAsProcess(paymentParameterObject);
        assertThat(next, is("SUCCESS"));
        String number = paymentParameterObject.getCustomerAccountNumber();
        assertThat(number, notNullValue());
    }
    
    @Test
    public void testTestMode() throws Exception {
        InitiatePaymentCommand identifyCustomerProcess = new InitiatePaymentCommand();
        identifyCustomerProcess.setProcessID("SUCCESS");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setTestmode(true);
        String next = identifyCustomerProcess.executeAsProcess(paymentParameterObject);
        assertThat(next, CoreMatchers.is("SUCCESS"));
        String breadCrumb = paymentParameterObject.getBreadCrumb();
        assertThat(breadCrumb, is(" => SUCCESS"));
    }

}
