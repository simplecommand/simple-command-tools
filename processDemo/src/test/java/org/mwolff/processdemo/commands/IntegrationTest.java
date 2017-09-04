package org.mwolff.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.chain.ChainBuilder;
import org.mwolff.command.chain.XMLChainBuilder;

public class IntegrationTest {

    @Test
    public void testPremium() throws Exception {
        ChainBuilder<PaymentParameterObject> xmlChainBuilder = new XMLChainBuilder<>("/process.xml");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setTestmode(true);
        paymentParameterObject.setCustomerAccountNumber("31");
        String result = xmlChainBuilder.executeAsProcess("START", paymentParameterObject);
        String breadcrumb = paymentParameterObject.getBreadCrumb();
        assertThat(breadcrumb, is(" => START => IDENTIFY_CUSTOMER => GET_DISCOUNT => CALCULATE_PRICE"));
        assertThat(result, is("END"));
        String number = paymentParameterObject.getCustomerAccountNumber();
        assertThat(number, is("31"));
    }

    @Test
    public void testFail() throws Exception {
        ChainBuilder<PaymentParameterObject> xmlChainBuilder = new XMLChainBuilder<>("/process.xml");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setTestmode(true);
        paymentParameterObject.setCustomerAccountNumber("30");
        String result = xmlChainBuilder.executeAsProcess("START", paymentParameterObject);
        String breadcrumb = paymentParameterObject.getBreadCrumb();
        assertThat(breadcrumb, is(" => START => IDENTIFY_CUSTOMER"));
        assertThat(result, is("END"));
        String number = paymentParameterObject.getCustomerAccountNumber();
        assertThat(number, is("30"));
    }
    @Test
    public void testNotAPremium() throws Exception {
        ChainBuilder<PaymentParameterObject> xmlChainBuilder = new XMLChainBuilder<>("/process.xml");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setTestmode(true);
        paymentParameterObject.setCustomerAccountNumber("20");
        String result = xmlChainBuilder.executeAsProcess("START", paymentParameterObject);
        String breadcrumb = paymentParameterObject.getBreadCrumb();
        assertThat(breadcrumb, is(" => START => IDENTIFY_CUSTOMER => NOT_A_PREMIUM => CALCULATE_PRICE"));
        assertThat(result, is("END"));
        String number = paymentParameterObject.getCustomerAccountNumber();
        assertThat(number, is("20"));
    }
}
