package org.mwolff.processdemo.commands;

import java.util.Random;

import org.mwolff.command.process.AbstractDefaultProcessCommand;

public class InitiatePaymentCommand extends AbstractDefaultProcessCommand<PaymentParameterObject>{

    @Override
    public String executeAsProcess(PaymentParameterObject context) {
        execute(context);
        return "SUCCESS";
    }
    
    @Override
    public void execute(PaymentParameterObject parameterObject) {
        Random rn = new Random();
        int n = Integer.MAX_VALUE;
        int i = rn.nextInt() % n;
        parameterObject.setCustomerAccountNumber(Integer.valueOf(i).toString());
    }

    @Override
    public boolean executeAsChain(PaymentParameterObject parameterObject) {
        return false;
    }

 }
