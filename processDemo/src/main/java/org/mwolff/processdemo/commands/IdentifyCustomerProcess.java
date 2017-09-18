package org.mwolff.processdemo.commands;

import org.mwolff.command.CommandTransitionEnum.CommandTransition;
import org.mwolff.command.process.AbstractDefaultProcessCommand;
import org.springframework.util.StringUtils;

public class IdentifyCustomerProcess extends AbstractDefaultProcessCommand<PaymentParameterObject>{

    @Override
    public String executeAsProcess(PaymentParameterObject context) {
        execute(context);
        if (context.isFail()) {
            return "FAIL";
        }
        if (context.isPremium()) {
            return "PREMIUM";
        }
        return "SUCCESS";
    }
    
    @Override
    public boolean executeAsChain(PaymentParameterObject parameterObject) {
        return false;
    }

    @Override
    public void execute(PaymentParameterObject parameterObject) {

        Integer integer = Integer.valueOf(parameterObject.getCustomerAccountNumber());
        if (modulo3(integer)) {
            parameterObject.setFail(true);
        } else {
            parameterObject.setFail(false);
            if (isEven(integer)) {
                parameterObject.setPremium(false);
            } else {
                parameterObject.setPremium(true);
            }
        }
        
        if (parameterObject.isTestmode()) {
            String breadCrumb = parameterObject.getBreadCrumb();
            if (StringUtils.isEmpty(breadCrumb)) breadCrumb = "";

            breadCrumb = breadCrumb + " => " + getProcessID();
            parameterObject.setBreadCrumb(breadCrumb);
        }
        
    }

    private boolean isEven(Integer integer) {
        return integer % 2 == 0;
    }

    private boolean modulo3(Integer integer) {
        return integer % 3 == 0;
    }

}
