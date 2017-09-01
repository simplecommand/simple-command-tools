package org.mwolff.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.chain.ChainBuilder;
import org.mwolff.command.chain.XMLChainBuilder;

public class IntegrationTest {

    @Test
    public void testAll() throws Exception {

        ChainBuilder<PaymentParameterObject> xmlChainBuilder = new XMLChainBuilder<>("/process.xml");
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        String result = xmlChainBuilder.executeAsProcess("START", paymentParameterObject);
        assertThat(result, is("END"));
        String number = paymentParameterObject.getCustomerAccountNumber();
        assertThat(number, notNullValue());
    }

}
