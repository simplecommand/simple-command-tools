package org.mwolff.processdemo.commands;

import java.util.Random;

import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.process.AbstractDefaultProcessCommand;
import org.springframework.util.StringUtils;

public class InitiatePaymentCommand extends AbstractDefaultProcessCommand<PaymentParameterObject> {

    @Override
    public String executeAsProcess(PaymentParameterObject context) {
        execute(context);
        return "SUCCESS";
    }

    @Override
    public void execute(PaymentParameterObject parameterObject) {

        if (!parameterObject.isTestmode()) {
            Random rn = new Random();
            int n = Integer.MAX_VALUE;
            int i = rn.nextInt() % n;
            parameterObject.setCustomerAccountNumber(Integer.valueOf(i).toString());
        } else {
            String breadCrumb = parameterObject.getBreadCrumb();
            if (StringUtils.isEmpty(breadCrumb)) breadCrumb = "";
            breadCrumb = breadCrumb + " => " + getProcessID();
            parameterObject.setBreadCrumb(breadCrumb);
        }
    }

    @Override
    public boolean executeAsChain(PaymentParameterObject parameterObject) {
        return false;
    }

    @Override
    public CommandTransition executeCommand(PaymentParameterObject parameterObject) {
        // TODO Auto-generated method stub
        return null;
    }
}
