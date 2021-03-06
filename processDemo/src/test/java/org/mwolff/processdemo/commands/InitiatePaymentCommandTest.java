package org.mwolff.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class InitiatePaymentCommandTest extends TestCase {
    
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
        InitiatePaymentCommand initiatePaymentCommand = new InitiatePaymentCommand();
        initiatePaymentCommand.setProcessID("INITIATE");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setTestmode(true);
        paymentParameterObject.setCustomerAccountNumber("100");
        String next = initiatePaymentCommand.executeAsProcess(paymentParameterObject);
        assertThat(next, is("SUCCESS"));
        String number = paymentParameterObject.getCustomerAccountNumber();
        assertThat(number, is("100"));
        String breadCrumb = paymentParameterObject.getBreadCrumb();
        assertThat(breadCrumb, is(" => INITIATE"));
    }

}
