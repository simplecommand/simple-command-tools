package org.mwolff.processdemo.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GetDiscountProcessCommandTest {
    
    @Test
    public void testDiscount() throws Exception {
        
        GetDiscountProcessCommand getDiscountProcessCommand = new GetDiscountProcessCommand();
        PaymentParameterObject paymentParameterObject = new PaymentParameterObject();
        paymentParameterObject.setValue(100.0);
        String next = getDiscountProcessCommand.executeAsProcess(paymentParameterObject);
        assertThat(next, is("SUCCESS"));
        double value = paymentParameterObject.getValue();
        assertThat(value, is(90.0));
    }


}
